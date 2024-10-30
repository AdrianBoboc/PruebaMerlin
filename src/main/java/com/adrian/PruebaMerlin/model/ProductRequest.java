package com.adrian.PruebaMerlin.model;

import java.util.List;

public class ProductRequest {

	private double salesWeight;
	private double stockWeight;
	private List<ProductSales> productSales;
	private List<ProductStock> productStock;

	public double getSalesWeight() {
		return salesWeight;
	}

	public void setSalesWeight(double salesWeight) {
		this.salesWeight = salesWeight;
	}

	public double getStockWeight() {
		return stockWeight;
	}

	public void setStockWeight(double stockWeight) {
		this.stockWeight = stockWeight;
	}

	public List<ProductSales> getProductSales() {
		return productSales;
	}

	public void setProductSales(List<ProductSales> productSales) {
		this.productSales = productSales;
	}

	public List<ProductStock> getProductStock() {
		return productStock;
	}

	public void setProductStock(List<ProductStock> productStock) {
		this.productStock = productStock;
	}
	
	
}
