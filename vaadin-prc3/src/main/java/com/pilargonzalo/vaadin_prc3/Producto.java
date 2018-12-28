package com.pilargonzalo.vaadin_prc3;

public class Producto {
	
	public  String nombre;
	public  Double precio;
	
	public Producto(String nombre, Double precio) {
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

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}




	
}
