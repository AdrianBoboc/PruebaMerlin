package com.adrian.PruebaMerlin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.adrian.PruebaMerlin.service.ProductService;
import com.adrian.PruebaMerlin.model.ProductRequest;

@RestController
@RequestMapping("/sort-products")
public class Controller {

	@Autowired
	private ProductService productService;
	
	
		// Endpoint de /short-products
		// Este devolverá, de mayor a menor importancia que se de a las ventas y al stock, el id para saber la forma de ordenar dichos productos
	@PostMapping		
	public List<String> sortProducts(@RequestBody ProductRequest productRequest) {
		return productService.sortProducts(productRequest);
	}
}
