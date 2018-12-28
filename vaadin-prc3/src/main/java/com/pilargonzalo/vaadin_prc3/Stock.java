package com.pilargonzalo.vaadin_prc3;
import java.util.ArrayList;
import java.util.List;

public class Stock {
	public List<Producto> productos;
	public Stock() {
		productos = new ArrayList<>();
	}
	
	//Añadir un producto al stock
	public void addProdToStock(Producto p) {
		productos.add(p);
	}
	
	//Eliminar un producto del stock
	public void deleteProdToStock(Producto p) {
		productos.remove(p);
	}	
	//Mostrar resumen del pedido
	public List<Producto> getProductos(){
		return productos;
	}
	

	
	
}
