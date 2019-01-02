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
	private Stock stock = new Stock();

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		/*
		 * VerticalLayout verticalLayout = new VerticalLayout();
		 * verticalLayout.setMargin(false); Label l = new Label("Pilar Molina Tirado");
		 * l.setContentMode(com.vaadin.shared.ui.ContentMode.HTML);
		 */
		Grid<Producto> grid = new Grid<Producto>();

		/* VENTADA DE DETALLES DEL PRODUCTO */
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		Window subWindow = new Window("Detalles del producto");
		VerticalLayout subContent = new VerticalLayout();
		Label labelNombre = new Label();
		Label labelPrecio = new Label();

		Button buttonDelete = new Button("Delete");
		Button buttonModificar = new Button("Modificar");
		TextField textFieldNuevoNombre = new TextField("Nombre");
		TextField textFieldNuevoPrecio = new TextField("Precio");

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
			Producto prod = new Producto(textFieldNuevoNombre.getValue(), nuevoPrecio);
			stock.addProdToStock(prod);
			textFieldNuevoNombre.clear();
			textFieldNuevoPrecio.clear();

			grid.setItems(stock.getProductos());

			Notification.show("Producto capturado! Ya tenemos " + stock.getProductos().size() + "!!",
					Notification.TYPE_TRAY_NOTIFICATION);
		});

		subContent.addComponents(labelNombre, labelPrecio, buttonDelete, textFieldNuevoNombre, textFieldNuevoPrecio,
				buttonModificar);

		subWindow.center();
		subWindow.setContent(subContent);
		// addWindow(subWindow);

		/* TABLA DE PRODUCTOS */
		/* INSERCIÓN EN LA TABLA */
		grid.addColumn(Producto::getNombre).setCaption("Nombre");
		grid.addColumn(Producto::getPrecio).setCaption("Precio");
		grid.setSelectionMode(SelectionMode.SINGLE);

		grid.addItemClickListener(event -> {
			selectedProducto = event.getItem();

			// Notification.show("Value: " + event.getItem());
			labelNombre.setValue(selectedProducto.getNombre());
			labelPrecio.setValue(selectedProducto.getPrecio().toString());

			removeWindow(subWindow);
			addWindow(subWindow);
		});

		/* FORMULARIO DE AÑADIR NUEVO PRODUCTO */
		FormLayout formLayout = new FormLayout();
		TextField textFieldNombre = new TextField("Nombre");
		TextField textFieldPrecio = new TextField("Precio");
		Button buttonAddProducto = new Button("Añadir");

		buttonAddProducto.addClickListener(e -> {
			double precio = Double.parseDouble(textFieldPrecio.getValue());
			Producto prod = new Producto(textFieldNombre.getValue(), precio);
			stock.addProdToStock(prod);
			textFieldNombre.clear();
			textFieldPrecio.clear();

			grid.setItems(stock.getProductos());
			Notification.show("Producto añadido! Ya tenemos " + stock.getProductos().size() + "!!");
		});
		
		formLayout.addComponents(textFieldNombre, textFieldPrecio, buttonAddProducto);

		horizontalLayout.addComponents(grid, formLayout);
		setContent(horizontalLayout);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {

	}

}
