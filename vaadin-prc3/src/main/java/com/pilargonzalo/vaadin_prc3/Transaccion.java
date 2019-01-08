package com.pilargonzalo.vaadin_prc3;


public class Transaccion {

	public  String nombre;
	public  int cantidad;
	private String fecha;
	private double beneficio;
	
	public Transaccion(String fecha, String nombre, int cantidad, double beneficio) {
		super(); 
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.beneficio = beneficio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(double beneficio) {
		this.beneficio = beneficio;
	}
	

}

