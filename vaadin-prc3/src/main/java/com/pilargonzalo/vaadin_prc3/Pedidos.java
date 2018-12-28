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
	public void deleteProdToP(Producto p) {
		productos.remove(p);
	}
	
	public void modificarProdToPed(Producto p, Producto n){
		Iterator<Producto> it = productos.iterator();
		while (it.hasNext()) {
			if((p).equals(it.next())) {
				System.out.println(p+"jlkjdskl"+ it.next());
				productos.remove(p);
				productos.add(n);
			}
		}

	}
	
	//Mostrar resumen del pedido
	public List<Producto> getProductos(){
		return productos;
	}
	

	
	
}
