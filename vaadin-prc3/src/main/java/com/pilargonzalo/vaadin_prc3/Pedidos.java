package com.pilargonzalo.vaadin_prc3;
import java.util.ArrayList;

public class Pedidos {
	public ArrayList<Producto> pedido;
	public Pedidos() {
		pedido = new ArrayList<>();
	}
	
	public void addPedido(Producto p) {
		pedido.add(p);
		
	}
	
	public void getPedido() {
		System.out.println("El pedido es el siguiente" + pedido);
	}

}
