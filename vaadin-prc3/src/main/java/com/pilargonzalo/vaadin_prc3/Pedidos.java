package com.pilargonzalo.vaadin_prc3;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pedidos {
	public List<Producto> productos;
	public Pedidos() {
		productos = new ArrayList<>();
	}
	
	//Añadir un producto al pedido
	public void addProdToPed(Producto p) {
		productos.add(p);
		
	}
	
	//Eliminar un producto del pedido
	public void deleteProdToPed(Producto p) {
		productos.remove(p);
	}	
	//Mostrar resumen del pedido
	public List<Producto> getProductos(){
		return productos;
	}
	

	
	
}
