package com.pilargonzalo.vaadin_prc3;

import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.ValueProvider;
import com.vaadin.server.AbstractErrorMessage.ContentMode;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;


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
	private Stock p = new Stock();

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		//AppLayout applayout = new AppLayout();
		
		
		Grid<Producto> grid = new Grid<Producto>();
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setMargin(false);
		Label l = new Label("Pilar Molina Tirado");
		l.setContentMode(com.vaadin.shared.ui.ContentMode.HTML);

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		Window subWindow = new Window("Detalles del producto");
		VerticalLayout subContent = new VerticalLayout();
		Label labelNombre = new Label();
		Label labelPrecio = new Label();

		Button buttonDelete = new Button("Delete");
		Button buttonModificar = new Button("Modificar");
		TextField textFieldNuevoNombre = new TextField("Nombre");
		TextField textFieldNuevoPrecio = new TextField("Precio");

		buttonDelete.addClickListener(e -> {
			p.deleteProdToPed(selectedProducto);
			grid.setItems(p.getProductos());
			removeWindow(subWindow);
		});
		
		buttonModificar.addClickListener(e -> {
			p.deleteProdToPed(selectedProducto);
			grid.setItems(p.getProductos());
			removeWindow(subWindow);
			Producto prod = new Producto(textFieldNuevoNombre.getValue(), textFieldNuevoPrecio.getValue());
			p.addProdToPed(prod);
			textFieldNuevoNombre.clear();
			textFieldNuevoPrecio.clear();
			grid.setItems(p.getProductos());	
		});

		subContent.addComponents(labelNombre, labelPrecio,buttonDelete, textFieldNuevoNombre,textFieldNuevoPrecio,buttonModificar);

		subWindow.center();
		subWindow.setContent(subContent);
		// addWindow(subWindow);

		/* TABLE */
		grid.addColumn(Producto::getNombre).setCaption("Nombre");
		grid.addColumn(Producto::getPrecio).setCaption("Precio");
		grid.setSelectionMode(SelectionMode.SINGLE);

		grid.addItemClickListener(event -> {

			selectedProducto = event.getItem();

			// Notification.show("Value: " + event.getItem());
			labelNombre.setValue(selectedProducto.getNombre());
			labelPrecio.setValue(selectedProducto.getPrecio());

			removeWindow(subWindow);
			addWindow(subWindow);

		});

		/* FORM */
		FormLayout formLayout = new FormLayout();
		TextField textFieldNombre = new TextField("Nombre");
		TextField textFieldPrecio = new TextField("Precio");
		Button buttonAddProducto = new Button("Añadir");

		buttonAddProducto.addClickListener(e -> {

			Producto prod = new Producto(textFieldNombre.getValue(), textFieldPrecio.getValue());

			p.addProdToPed(prod);

			textFieldNombre.clear();
			textFieldPrecio.clear();

			grid.setItems(p.getProductos());

			Notification.show("Producto capturado! Ya tenemos " + p.getProductos().size() + "!!",
					Notification.TYPE_TRAY_NOTIFICATION);

		});

		formLayout.addComponents(textFieldNombre, textFieldPrecio, buttonAddProducto);

		horizontalLayout.addComponents(l, grid, formLayout);
		
		TabSheet tab = new TabSheet();
		VerticalLayout tab1 = new VerticalLayout();
		tab.addTab(tab1, "Etiqueta 1");
		VerticalLayout tab2 = new VerticalLayout();
		tab.addTab(tab2, "Etiqueta 2");
		tab.addTab(formLayout, "Formulario");
		tab.addTab(grid, "Tabla");
		setContent(tab);

	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {

	}

}
