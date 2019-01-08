package com.pilargonzalo.vaadin_prc3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class Pruebas {

	@Before
	public void vaciarpruebas() {
		Stock productos = Stock.getInstance();
		productos.vaciar();
		assertEquals(productos.getProductos().size(), 0);
	}
	
	@Test
	public void addProducto() {
		Stock stock = Stock.getInstance();
		Producto prodPrueba = new Producto("producto prueba", 45.36, 23, null);
		stock.addProdToStock(prodPrueba);
		assertEquals(stock.getProductos().size(), 1);
	}
	
	
	
	@Test
	public void delProducto() {
		Stock stock = Stock.getInstance();
		stock.vaciar();
		Producto prodPrueba = new Producto("producto prueba", 45.36, 23, null);
		stock.addProdToStock(prodPrueba);
		
		stock.deleteProdToStock(prodPrueba);
		assertEquals(stock.getProductos().size(), 0);
	}
	
	@Test
	public void modProducto() {
		Stock stock = Stock.getInstance();
		stock.vaciar();
		Producto prodPrueba = new Producto("producto prueba", 45.36, 23, null);
		stock.addProdToStock(prodPrueba);
		
		stock.deleteProdToStock(prodPrueba);
		Producto prodPruebamodificado = new Producto("producto prueba modificado", 45.36, 23, null);
		stock.addProdToStock(prodPrueba);
		assertEquals(stock.getProductos().size(), 1);
		
		
	}

}
