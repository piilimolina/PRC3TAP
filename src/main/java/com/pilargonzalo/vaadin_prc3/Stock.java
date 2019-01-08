package com.pilargonzalo.vaadin_prc3;
import java.util.ArrayList;


public class Stock{

    private static Stock singleton;
    private ArrayList<Producto> productos = new ArrayList<>();

    //private
    public Stock() {
        
    }

    public static Stock getInstance() {
        if(singleton == null)
            singleton = new Stock();

        return singleton;
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
	public ArrayList<Producto> getProductos(){
		return productos;
	}
}