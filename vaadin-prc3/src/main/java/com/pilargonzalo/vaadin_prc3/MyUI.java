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

		Grid<Producto> gridP = new Grid<Producto>();
		Grid<Componente> gridC = new Grid<Componente>();

		VerticalLayout verticalLayout = new VerticalLayout();
		HorizontalLayout horizontalLayout = new HorizontalLayout();

		
		
		
		
		// ----------------------------------------------------------------------------------//
		// --------------------------------- POP UP ----------------------------------------//
		// --------------------------------------------------------------------------------//
		
		/* ---------- VENTADA DE DETALLES DEL PRODUCTO ---------- */
		Window subWindow = new Window("Detalles del producto");
		VerticalLayout subContent = new VerticalLayout();
		Label labelNombre = new Label();
		Label labelPrecio = new Label();
		Label labelCantidad = new Label();
		
		
		
		
		
		// ----------------------------------------------------------------------------------//
		// --------------------------------- TABLAS ----------------------------------------//
		// --------------------------------------------------------------------------------//
		
		/* ---------- TABLA DE PRODUCTOS ---------- */
		/* ---------- INSERCIÓN EN LA TABLA ---------- */
		gridP.setCaption("Lista de Productos");
		gridP.addColumn(Producto::getNombre).setCaption("Nombre");
		gridP.addColumn(Producto::getPrecio).setCaption("Precio");
		gridP.addColumn(Producto::getCantidad).setCaption("Cantidad");
		gridP.setSelectionMode(SelectionMode.SINGLE);

		gridP.addItemClickListener(event -> {
			selectedProducto = event.getItem();

			// Notification.show("Value: " + event.getItem());
			labelNombre.setValue(selectedProducto.getNombre());
			labelPrecio.setValue(selectedProducto.getPrecio().toString());
			labelCantidad.setValue(String.valueOf(selectedProducto.getCantidad()));

			removeWindow(subWindow);
			addWindow(subWindow);
		});

		/* ---------- TABLA DE COMPONENTES ---------- */
		/* ---------- INSERCION EN LA TABLA ---------- */
		gridC.setCaption("Componentes");
		gridC.addColumn(Componente::getNombreComp).setCaption("Nombre");
		gridC.addColumn(Componente::getCantidadComp).setCaption("Cantidad");
		
		
		


		// ----------------------------------------------------------------------------------//
		// ---------------------------------- FORMS ----------------------------------------//
		// --------------------------------------------------------------------------------//
		
		/* ---------- FORMULARIO DE AÑADIR NUEVO PRODUCTO ---------- */
		/* ---------- Y SUS COMPONENTES ---------- */
		FormLayout formLayout = new FormLayout();
		formLayout.setCaption("Formulario Añadir Producto");
		TextField textFieldNombre = new TextField("Nombre");
		TextField textFieldPrecio = new TextField("Precio");
		TextField textFieldCantidad = new TextField("Cantidad");

		TextField textFieldNombreComp = new TextField("Nombre");
		TextField textFieldCantidadComp = new TextField("Cantidad");

		
		
		
		
		
		// ----------------------------------------------------------------------------------//
		// -------------------------------- BUTTONS ----------------------------------------//
		// --------------------------------------------------------------------------------//
		TextField textFieldNuevoNombre = new TextField("Nombre");
		TextField textFieldNuevoPrecio = new TextField("Precio");
		TextField textFieldNuevoCantidad = new TextField("Cantidad");

		
		/* ---------- FUNCIONALIDAD BOTÓN "DELETE" ---------- */
		Button buttonDelete = new Button("Delete");
		buttonDelete.addClickListener(e -> {
			stock.deleteProdToStock(selectedProducto);
			gridP.setItems(stock.getProductos());
			removeWindow(subWindow);
		});
		

		/* ---------- FUNCIONALIDAD BOTÓN "MODIFICAR" ---------- */
		Button buttonModificar = new Button("Modificar");
		buttonModificar.addClickListener(e -> {
			stock.deleteProdToStock(selectedProducto);
			gridP.setItems(stock.getProductos());
			removeWindow(subWindow);

			double nuevoPrecio = Double.parseDouble(textFieldNuevoPrecio.getValue());
			int nuevaCantidad = Integer.parseInt(textFieldNuevoCantidad.getValue());

			Producto prod = new Producto(textFieldNuevoNombre.getValue(), nuevoPrecio, nuevaCantidad);
			stock.addProdToStock(prod);
			textFieldNuevoNombre.clear();
			textFieldNuevoPrecio.clear();
			textFieldNuevoCantidad.clear();

			gridP.setItems(stock.getProductos());

			Notification.show("Producto capturado! Ya tenemos " + stock.getProductos().size() + "!!",
					Notification.TYPE_TRAY_NOTIFICATION);
		});

		subContent.addComponents(labelNombre, labelPrecio, labelCantidad, buttonDelete, textFieldNuevoNombre,
				textFieldNuevoPrecio, textFieldNuevoCantidad, buttonModificar);
		subWindow.center();
		subWindow.setContent(subContent);

		
		/* ---------- FUNCIONALIDAD BOTÓN "AÑADIR PRODUCTO" ---------- */
		Button buttonAddProducto = new Button("Añadir Producto");
		buttonAddProducto.addClickListener(e -> {
			double precio = Double.parseDouble(textFieldPrecio.getValue());
			int cantidad = Integer.parseInt(textFieldCantidad.getValue());
			Producto prod = new Producto(textFieldNombre.getValue(), precio, cantidad);

			stock.addProdToStock(prod);
			textFieldNombre.clear();
			textFieldPrecio.clear();
			textFieldCantidad.clear();

			gridP.setItems(stock.getProductos());
			Notification.show("Producto añadido! Ya tenemos " + stock.getProductos().size() + "!!");
		});

		
		/* ---------- FUNCIONALIDAD BOTÓN "AÑADIR COMPONENTE" ---------- */
		Button buttonAddComponente = new Button("Añadir Componente");
		buttonAddComponente.addClickListener(c -> {
			int cantidadComp = Integer.parseInt(textFieldCantidad.getValue());
			Componente comp = new Componente(textFieldNombre.getValue(), cantidadComp);

			textFieldNombreComp.clear();
			textFieldCantidadComp.clear();

			Notification.show("Componente añadido!" /* Ya tenemos " + stock.getProductos().size() + "!!" */);

		});

		
		/* ---------- FUNCIONALIDAD BOTÓN "CAMBIAR DIVISA" ---------- */
		Button ButtonMoneda = new Button("Cambio de Divisa");
		final String Euros = "€";
		final String Dollars = "$";

		Label labelDivisa = new Label();
		labelDivisa.setCaption("Moneda actual:\t\t" + Euros);

		ButtonMoneda.addClickListener(e -> {
			if (moneda == 0) { // € to $
				for (Producto prod : stock.getProductos()) {
					prod.setPrecio(prod.getPrecio() * 1.2);
				}
				labelDivisa.setCaption("Moneda actual:\t\t" + Dollars);
				moneda = 1;
			} else { // $ to €
				for (Producto prod : stock.getProductos()) {
					prod.setPrecio(prod.getPrecio() / 1.2);
				}
				labelDivisa.setCaption("Moneda actual:\t\t" + Euros);
				moneda = 0;
			}
			gridP.setItems(stock.getProductos());
		});

		
		
		
		// ----------------------------------------------------------------------------------//
		// ------------------------ GENERACIÓN EN PANTALLA ---------------------------------//
		// --------------------------------------------------------------------------------//
		formLayout.addComponents(textFieldNombre, textFieldPrecio, textFieldCantidad, buttonAddProducto,
				textFieldNombreComp, textFieldCantidadComp, buttonAddComponente);
		horizontalLayout.addComponents(gridP, formLayout, gridC);
		verticalLayout.addComponents(ButtonMoneda, labelDivisa, horizontalLayout);

		setContent(verticalLayout);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {

	}

}
