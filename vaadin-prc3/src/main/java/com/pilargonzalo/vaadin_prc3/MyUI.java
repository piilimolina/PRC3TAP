package com.pilargonzalo.vaadin_prc3;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */

@Theme("mytheme")
public class MyUI extends UI {
	private Producto selectedProducto;
	private Stock stock = Stock.getInstance();
	private int moneda = 0;

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		Grid<Producto> grid = new Grid<Producto>();

		VerticalLayout verticalLayout = new VerticalLayout();
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		
		
		/* VENTADA DE DETALLES DEL PRODUCTO */
		Window subWindow = new Window("Detalles del producto");
		VerticalLayout subContent = new VerticalLayout();
		Label labelNombre = new Label();
		Label labelPrecio = new Label();
		Label labelCantidad = new Label();

		Button buttonDelete = new Button("Delete");
		Button buttonModificar = new Button("Modificar");
		TextField textFieldNuevoNombre = new TextField("Nombre");
		TextField textFieldNuevoPrecio = new TextField("Precio");
		TextField textFieldNuevoCantidad = new TextField("Cantidad");

		/* FUNCIONALIDAD BOTÓN "DELETE" */
		buttonDelete.addClickListener(e -> {
			stock.deleteProdToStock(selectedProducto);
			grid.setItems(stock.getProductos());
			removeWindow(subWindow);
		});

		/* FUNCIONALIDAD BOTÓN "MODIFICAR" */
		buttonModificar.addClickListener(e -> {
			stock.deleteProdToStock(selectedProducto);
			grid.setItems(stock.getProductos());
			removeWindow(subWindow);

			double nuevoPrecio = Double.parseDouble(textFieldNuevoPrecio.getValue());
			int nuevaCantidad = Integer.parseInt(textFieldNuevoCantidad.getValue());
			
			Producto prod = new Producto(textFieldNuevoNombre.getValue(), nuevoPrecio, nuevaCantidad);
			stock.addProdToStock(prod);
			textFieldNuevoNombre.clear();
			textFieldNuevoPrecio.clear();
			textFieldNuevoCantidad.clear();

			grid.setItems(stock.getProductos());

			Notification.show("Producto capturado! Ya tenemos " + stock.getProductos().size() + "!!",
					Notification.TYPE_TRAY_NOTIFICATION);
		});

		subContent.addComponents(labelNombre, labelPrecio, labelCantidad, buttonDelete, textFieldNuevoNombre, textFieldNuevoPrecio, textFieldNuevoCantidad, buttonModificar);

		subWindow.center();
		subWindow.setContent(subContent);
		// addWindow(subWindow);

		/* TABLA DE PRODUCTOS */
		/* INSERCIÓN EN LA TABLA */
		grid.setCaption("Lista de Productos");
		grid.addColumn(Producto::getNombre).setCaption("Nombre");
		grid.addColumn(Producto::getPrecio).setCaption("Precio");
		grid.addColumn(Producto::getCantidad).setCaption("Cantidad");
		grid.setSelectionMode(SelectionMode.SINGLE);

		grid.addItemClickListener(event -> {
			selectedProducto = event.getItem();

			// Notification.show("Value: " + event.getItem());
			labelNombre.setValue(selectedProducto.getNombre());
			labelPrecio.setValue(selectedProducto.getPrecio().toString());
			labelCantidad.setValue(String.valueOf(selectedProducto.getCantidad()));

			removeWindow(subWindow);
			addWindow(subWindow);
		});

		/* FORMULARIO DE AÑADIR NUEVO PRODUCTO */
		FormLayout formLayout = new FormLayout();
		formLayout.setCaption("Formulario Añadir Producto");
		TextField textFieldNombre = new TextField("Nombre");
		TextField textFieldPrecio = new TextField("Precio");
		TextField textFieldCantidad = new TextField("Cantidad");
		Button buttonAddProducto = new Button("Añadir");

		buttonAddProducto.addClickListener(e -> {
			double precio = Double.parseDouble(textFieldPrecio.getValue());
			int cantidad = Integer.parseInt(textFieldCantidad.getValue());
			Producto prod = new Producto(textFieldNombre.getValue(), precio, cantidad);
			
			stock.addProdToStock(prod);
			textFieldNombre.clear();
			textFieldPrecio.clear();
			textFieldCantidad.clear();

			grid.setItems(stock.getProductos());
			Notification.show("Producto añadido! Ya tenemos " + stock.getProductos().size() + "!!");
		});
		
		/* BOTÓN CAMBIO DE DIVISA */
		Button ButtonMoneda = new Button("Cambio de Divisa");
		final String Euros = "€";
		final String Dollars = "$";
		
		Label labelDivisa = new Label();
		labelDivisa.setCaption("Moneda actual:\t\t" + Euros);
		
		ButtonMoneda.addClickListener(e ->{
			if (moneda == 0) { // € to $
				for(Producto prod : stock.getProductos()) {
					prod.setPrecio(prod.getPrecio() * 1.2);
				}
				labelDivisa.setCaption("Moneda actual:\t\t" + Dollars);
				moneda = 1;
			}else{ // $ to €
				for(Producto prod : stock.getProductos()) {
					prod.setPrecio(prod.getPrecio() / 1.2);
				}
				labelDivisa.setCaption("Moneda actual:\t\t" + Euros);
				moneda = 0;
			}
			grid.setItems(stock.getProductos());
		});
		
		formLayout.addComponents(textFieldNombre, textFieldPrecio, textFieldCantidad, buttonAddProducto);

		horizontalLayout.addComponents(grid, formLayout);
		verticalLayout.addComponents(ButtonMoneda, labelDivisa, horizontalLayout);
		setContent(verticalLayout);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {

	}

}
