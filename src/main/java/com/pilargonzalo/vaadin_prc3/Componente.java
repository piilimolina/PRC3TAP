package com.pilargonzalo.vaadin_prc3;

public class Componente {
	
	private String nombreComp;
	private int cantidadComp;
	
	public Componente(String nombreComp, int cantidadComp) {
		// TODO Auto-generated constructor stub
		super();
		this.nombreComp = nombreComp;
		this.cantidadComp = cantidadComp;
	}
	
	
	public String getNombreComp() {
		return nombreComp;
	}

	public void setNombreComp(String nombreComp) {
		this.nombreComp = nombreComp;
	}

	public int getCantidadComp() {
		return cantidadComp;
	}

	public void setCantidadComp(int cantidadComp) {
		this.cantidadComp = cantidadComp;
	}


}
