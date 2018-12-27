package com.pilargonzalo.vaadin_prc3;

public class Producto {
	
	public  String nombre;
	public  String precio;
	
	public Producto(String nombre, String precio) {
		super(); 
		this.nombre = nombre;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}




	
}
