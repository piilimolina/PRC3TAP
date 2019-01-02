package com.pilargonzalo.vaadin_prc3;

import java.util.ArrayList;

public class Producto {
	
	public  String nombre;
	public  Double precio;
	public  int cantidad;
	private ArrayList<Componente> componente = new ArrayList<>();
	
	public Producto(String nombre, Double precio, int cantidad) {
		super(); 
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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

	public ArrayList<Componente> getComponente() {
		return componente;
	}

	public void setComponente(ArrayList<Componente> componente) {
		this.componente = componente;
	}




	
}
