package com.sedex.marketplace.service;

import java.util.List;

import com.sedex.marketplace.model.json.Product;

public interface CheckoutService {

	public Double accept(String input);
	public List<Product> getItems();
	public void scan(Product product);
	public Double getOriginalPrice();
	public Double total();
}
