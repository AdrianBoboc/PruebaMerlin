package com.adrian.PruebaMerlin.service;

import com.adrian.PruebaMerlin.model.ProductRequest;
import com.adrian.PruebaMerlin.model.ProductSales;
import com.adrian.PruebaMerlin.model.ProductStock;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

	public List<String> sortProducts(ProductRequest productRequest){
		
			// Creo un mapa de ventas para tener asociados el id y las ventas en un mapa y poder tratarlos mejor
		Map<String, Double> salesMap = productRequest.getProductSales().stream()
                .collect(Collectors.toMap(
                    ProductSales::getProductId,
                    ProductSales::getSales
                ));
		
			// Creo un mapa de stock para tener asociados el id y el numero de stock en un mapa y poder tratarlos mejor
		Map<String, Integer> stockMap = productRequest.getProductStock().stream()
				.collect(Collectors.toMap(
					ProductStock::getProductId, 
					ProductStock::getStock));
			
		
			// Creo un mapa de puntuacion vacio para poder rellenarlo posteriormente
		Map<String, Double> productScores = new HashMap<>();
				
			// Calculo la puntuacion de cada producto, doy por hecho de que la cantidad de productos que hay en stock es la misma que la cantidad que se han vendido
			// Me refiero a los productId, que existan en ambas entidades, si está el productId 2828 en una, doy por hecho que estará en la otra
		for (String productId : salesMap.keySet()) {
			double sales = salesMap.getOrDefault(productId, 0.0);
			int stock = stockMap.getOrDefault(productId, 0);
			
				// Aqui sumo el puntuaje total de cada producto 
			double score = sales * productRequest.getSalesWeight() + stock * productRequest.getStockWeight();
			productScores.put(productId, score);
		}
		
			// Ordeno los productos por puntuacion descendente (de mayor a menor) y devuelvo los resultados como una lista
		return productScores.entrySet().stream()
				.sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
				.map(Map.Entry::getKey).collect(Collectors.toList());
	}
}
