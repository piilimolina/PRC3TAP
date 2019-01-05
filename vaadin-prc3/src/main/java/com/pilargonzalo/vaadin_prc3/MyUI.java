package com.pilargonzalo.vaadin_prc3;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
<<<<<<< HEAD
import com.vaadin.data.ValueProvider;
import com.vaadin.server.AbstractErrorMessage.ContentMode;
=======
import com.vaadin.icons.VaadinIcons;
>>>>>>> Integración
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
<<<<<<< HEAD
	private Stock p = new Stock();

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		//AppLayout applayout = new AppLayout();
		
		
		Grid<Producto> grid = new Grid<Producto>();
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setMargin(false);
		Label l = new Label("Pilar Molina Tirado");
		l.setContentMode(com.vaadin.shared.ui.ContentMode.HTML);

=======
	private Stock stock = Stock.getInstance();

	private ArrayList<Componente> componente = new ArrayList<>();
	private ArrayList<Transaccion> transacciones = new ArrayList<>();

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YY");
	private String fecha = sdf.format(new Date()); 
	
	private double situEconomica = 0;
	private int moneda = 0;
	final String Euros = "€";
	final String Dollars = "$";

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		VerticalLayout verticalLayout = new VerticalLayout();
>>>>>>> Integración
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		HorizontalLayout horizontalLayout2 = new HorizontalLayout();

		
		
		// ----------------------------------------------------------------------------------//
		// --------------------------------- POP UP ----------------------------------------//
		// --------------------------------------------------------------------------------//

		/* ---------- VENTADA DE DETALLES DEL PRODUCTO ---------- */
		Window subWindow = new Window("Detalles del producto");
		VerticalLayout subContent = new VerticalLayout();
		Label labelNombre = new Label();
		Label labelPrecio = new Label();
		Label labelCantidad = new Label();

		
		/* ---------- SITUACIÓN ECONÓNMICA ---------- */
		Label labelSituacionEconomica = new Label();
		labelSituacionEconomica.setCaption("La situación económica es:\t\t" + situEconomica + Euros);
		
		// ----------------------------------------------------------------------------------//
		// --------------------------------- TABLAS ----------------------------------------//
		// --------------------------------------------------------------------------------//

		Grid<Producto> gridP = new Grid<Producto>();
		Grid<Componente> gridC = new Grid<Componente>();
		Grid<Transaccion> gridT = new Grid<Transaccion>();
		
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

			gridC.setItems(selectedProducto.getComponente());
		});

		/* ---------- TABLA DE COMPONENTES ---------- */
		/* ---------- INSERCION EN LA TABLA ---------- */
		
		gridC.setCaption("Componentes");
		gridC.addColumn(Componente::getNombreComp).setCaption("Nombre");
		gridC.addColumn(Componente::getCantidadComp).setCaption("Cantidad");

		
		/* ---------- TABLA HISTÓRICA ---------- */
		/* ---------- INSERCION EN LA TABLA ---------- */
		gridT.setCaption("Histórico");
		gridT.addColumn(Transaccion::getFecha).setCaption("Fecha");
		gridT.addColumn(Transaccion::getNombre).setCaption("Nombre");
		gridT.addColumn(Transaccion::getCantidad).setCaption("Cantidad");
		gridT.addColumn(Transaccion::getBeneficio).setCaption("Beneficio");

		
		
		
		
		// ----------------------------------------------------------------------------------//
		// -------------------------------- FORMLAYOUT -------------------------------------//
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

		/* ---------- FORMULARIO DE AÑADIR NUEVO CRAFTEO ---------- */
		FormLayout formLayout2 = new FormLayout();
		formLayout2.setCaption("Formulario Del Crafteo");
		TextField textFieldCrafteo = new TextField("Producto a Craftear");
		TextField textFieldCantidadCrafteo = new TextField("Número de Crafteos");
		textFieldCantidadCrafteo.setValue("1");
		
		/* ---------- FORMULARIO DE INGRESOS/GASTOS MANUALES ---------- */
		FormLayout formLayout3 = new FormLayout();
		TextField textFieldIngreso = new TextField("Ingreso Manual");
		textFieldIngreso.setValue("0");
		TextField textFieldGasto = new TextField("Gasto Manual");
		textFieldGasto.setValue("0");
		
		
		
		
		// ----------------------------------------------------------------------------------//
		// -------------------------------- BUTTONS ----------------------------------------//
		// --------------------------------------------------------------------------------//
		TextField textFieldNuevoNombre = new TextField("Nombre");
		TextField textFieldNuevoPrecio = new TextField("Precio");
		TextField textFieldNuevoCantidad = new TextField("Cantidad");

<<<<<<< HEAD
		buttonDelete.addClickListener(e -> {
			p.deleteProdToStock(selectedProducto);
			grid.setItems(p.getProductos());
			removeWindow(subWindow);
		});
		
		buttonModificar.addClickListener(e -> {
			p.deleteProdToStock(selectedProducto);
			grid.setItems(p.getProductos());
			removeWindow(subWindow);
			Producto prod = new Producto(textFieldNuevoNombre.getValue(), textFieldNuevoPrecio.getValue());
			p.addProdToStock(prod);
			textFieldNuevoNombre.clear();
			textFieldNuevoPrecio.clear();
			grid.setItems(p.getProductos());	
		});

		subContent.addComponents(labelNombre, labelPrecio,buttonDelete, textFieldNuevoNombre,textFieldNuevoPrecio,buttonModificar);

=======
		/* ---------- FUNCIONALIDAD BOTÓN "DELETE" ---------- */
		Button buttonDelete = new Button("Delete", VaadinIcons.TRASH);
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

			Producto prod = new Producto(textFieldNuevoNombre.getValue(), nuevoPrecio, nuevaCantidad, null);
			stock.addProdToStock(prod);
			textFieldNuevoNombre.clear();
			textFieldNuevoPrecio.clear();
			textFieldNuevoCantidad.clear();

			gridP.setItems(stock.getProductos());
			Notification.show("Producto modificado!");
		});

		subContent.addComponents(labelNombre, labelPrecio, labelCantidad, buttonDelete, textFieldNuevoNombre,
				textFieldNuevoPrecio, textFieldNuevoCantidad, buttonModificar);
<<<<<<< HEAD
>>>>>>> Integración
		subWindow.center();
=======
		subWindow.setPosition(1500, 10);;
>>>>>>> Integración
		subWindow.setContent(subContent);

<<<<<<< HEAD
		/* TABLE */
		grid.addColumn(Producto::getNombre).setCaption("Nombre");
		grid.addColumn(Producto::getPrecio).setCaption("Precio");
		grid.setSelectionMode(SelectionMode.SINGLE);

		grid.addItemClickListener(event -> {

			selectedProducto = event.getItem();
=======
		/* ---------- FUNCIONALIDAD BOTÓN "AÑADIR PRODUCTO" ---------- */
		Button buttonAddProducto = new Button("Añadir Producto", VaadinIcons.EXCHANGE);
		buttonAddProducto.addClickListener(e -> {
			ArrayList<Componente> reset = new ArrayList<>();
			double precio = Double.parseDouble(textFieldPrecio.getValue());
			int cantidad = Integer.parseInt(textFieldCantidad.getValue());
			Producto prod = new Producto(textFieldNombre.getValue(), precio, cantidad, componente);
			Transaccion tran = new Transaccion(fecha, textFieldNombre.getValue(), cantidad, precio*cantidad*-1);
			transacciones.add(tran);
			
			situEconomica = situEconomica + precio*cantidad*-1; 
			labelSituacionEconomica.setCaption("La situación económica es:\t\t" + situEconomica +  Euros);

			stock.addProdToStock(prod);
			textFieldNombre.clear();
			textFieldPrecio.clear();
			textFieldCantidad.clear();
<<<<<<< HEAD
			componente = reset;
>>>>>>> Integración
=======
>>>>>>> Integración

			gridP.setItems(stock.getProductos());			
			gridC.setItems(componente);
			gridT.setItems(transacciones);
			
			componente = reset;
			gridC.setItems(componente);
			Notification.show("Producto añadido! Ya tenemos " + stock.getProductos().size() + "!!");
		});

		/* ---------- FUNCIONALIDAD BOTÓN "AÑADIR COMPONENTE" ---------- */
		Button buttonAddComponente = new Button("Añadir Componente", VaadinIcons.PLUS_CIRCLE);
		buttonAddComponente.addClickListener(e -> {
			int cantidadComp = Integer.parseInt(textFieldCantidadComp.getValue());
			Componente comp = new Componente(textFieldNombreComp.getValue(), cantidadComp);

			componente.add(comp);
			textFieldNombreComp.clear();
			textFieldCantidadComp.clear();

			gridC.setItems(componente);
			Notification.show("Componente añadido!");

<<<<<<< HEAD
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

			p.addProdToStock(prod);

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

=======
		});

		/* ---------- FUNCIONALIDAD BOTÓN "CAMBIAR DIVISA" ---------- */
		Button ButtonMoneda = new Button("Cambio de Divisa", VaadinIcons.MONEY_EXCHANGE);


		Label labelDivisa = new Label();
		labelDivisa.setCaption("Moneda actual:\t\t" + Euros);

		ButtonMoneda.addClickListener(e -> {
			if (moneda == 0) { // € to $
				for (Producto prod : stock.getProductos()) {
					prod.setPrecio(prod.getPrecio() * 1.2);
				}
				for(Transaccion tran: transacciones) {
					tran.setBeneficio(tran.getBeneficio() * 1.2);
				}
				
				labelDivisa.setCaption("Moneda actual:\t\t" + Dollars);
				moneda = 1;
			} else { // $ to €
				for (Producto prod : stock.getProductos()) {
					prod.setPrecio(prod.getPrecio() / 1.2);
				}
				for(Transaccion tran: transacciones) {
					tran.setBeneficio(tran.getBeneficio() * 1.2);
				}
				
				labelDivisa.setCaption("Moneda actual:\t\t" + Euros);
				moneda = 0;
			}
			gridP.setItems(stock.getProductos());
		});
		
		
		
		
		

		/* ---------- FUNCIONALIDAD BOTÓN "CRAFTEAR PRODUCTO" ---------- */
		Button ButtonProdCraft = new Button("Craftear Producto", VaadinIcons.COGS);
		
		ButtonProdCraft.addClickListener(e -> {
			ArrayList<Componente> auxiliar = new ArrayList<>();
			int cantCrafteos = Integer.parseInt(textFieldCantidadCrafteo.getValue());
			double precioComponentes = 0;
			
			
			for (Producto prod : stock.getProductos()) {
				if (textFieldCrafteo.getValue().toString().equals(prod.getNombre()) && prod.getComponente() != null) {
					gridC.setItems(prod.getComponente());
					
					for (Componente comp : prod.getComponente()) {
						for (Producto prodAux : stock.getProductos()) {
							if(comp.getNombreComp().equals(prodAux.getNombre()) && prodAux.getCantidad()>=comp.getCantidadComp()*cantCrafteos) {
								auxiliar.add(comp);
								
							} else {
								Notification.show("No se pueden craftear ese producto!!!");
							}
						}
					}
					
					if(auxiliar.size() == prod.getComponente().size()) {
						for (Componente comp : prod.getComponente()) {
							for (Producto prodAux : stock.getProductos()) {
								if(comp.getNombreComp().equals(prodAux.getNombre())){
									prodAux.setCantidad(prodAux.getCantidad() - comp.getCantidadComp()*cantCrafteos);
									precioComponentes =+  prodAux.getPrecio();
								}
							}
						}
						prod.setCantidad(prod.getCantidad() + 1*cantCrafteos);
						
						Transaccion tran = new Transaccion(fecha, textFieldCrafteo.getValue(), prod.getCantidad(), prod.getPrecio() - precioComponentes);
						transacciones.add(tran);
						
						situEconomica = situEconomica + (prod.getPrecio() - precioComponentes); 
						labelSituacionEconomica.setCaption("La situación económica es:\t\t" + situEconomica + Euros);
					}
					
				}
				
			}

			gridP.setItems(stock.getProductos());
			gridT.setItems(transacciones);
		});
		
		

		/* ---------- FUNCIONALIDAD BOTÓN "INGRESO/GASTO" ---------- */
		Button buttonIngresoGasto = new Button("Efectuar", VaadinIcons.CASH);
		buttonIngresoGasto.addClickListener(e -> {
			double ingreso = Double.parseDouble(textFieldIngreso.getValue());
			double gasto = Double.parseDouble(textFieldGasto.getValue());
			
			situEconomica = situEconomica + ingreso - gasto;
			labelSituacionEconomica.setCaption("La situación económica es:\t\t" + situEconomica + Euros);
			
			Notification.show("Ingreso/Gasto efectuado!");

		});
		
		
		
		
		
		
		// ----------------------------------------------------------------------------------//
		// ------------------------------------- FRONT -------------------------------------//
		// --------------------------------------------------------------------------------//
		formLayout.addComponents(textFieldNombre, textFieldPrecio, textFieldCantidad, buttonAddProducto,
				textFieldNombreComp, textFieldCantidadComp, buttonAddComponente);

		formLayout2.addComponents(textFieldCrafteo, textFieldCantidadCrafteo);
		
		formLayout3.addComponents(textFieldIngreso, textFieldGasto, buttonIngresoGasto);

		horizontalLayout.addComponents(gridP, formLayout, gridC);
		horizontalLayout2.addComponents(formLayout2);
		verticalLayout.addComponents(ButtonMoneda, labelDivisa, horizontalLayout, horizontalLayout2, ButtonProdCraft, 
				gridT, labelSituacionEconomica, formLayout3);

		setContent(verticalLayout);
>>>>>>> Integración
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {

	}

}
