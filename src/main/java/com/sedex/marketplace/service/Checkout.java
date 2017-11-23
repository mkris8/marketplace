package com.sedex.marketplace.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;

import com.sedex.marketplace.model.PromotionalRules;
import com.sedex.marketplace.model.json.Product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Checkout implements CheckoutService {
	private PromotionalRules promotionalRules;
	List<Product> items = new ArrayList<Product>();
	private Double originalPrice = 0.00;
	private Double salePrice;
	private final int TEN_DIS_AMT = 60;
	private final int TRAVAL_DIS_COUNT = 2;
	
	private static Logger logger = LoggerFactory.getLogger(Checkout.class);
	
	private ProductLoaderService loader = new ProductLoader();

	public Checkout(PromotionalRules promotionalRules) {
		this.promotionalRules = promotionalRules;
	}

	public List<Product> getItems() {
		return this.items;
	}

	public void scan(Product item) {
		this.items.add(item);
	}

	public Double getOriginalPrice() {
		for (int i = 0; i < items.size(); i++) {
			String price = items.get(i).getPrice();
			String formattedPrice = (price.contains("\u00a3") ? (price.replaceAll("\\£", "")) : price);
			Double originalPrice = Double.parseDouble(formattedPrice);
			this.originalPrice += originalPrice;
		}

		return this.originalPrice;
	}

	public Double accept(String input) {

		String regexp = "[\\s,:]+";
		List<String> inputCodes = Arrays.asList(input.split(regexp));
		List<Product> itemList = new ArrayList<Product>();

		List<JSONObject> jsonObjectList = loader.load();
		
		for (int i = 0; i < inputCodes.size(); i++) {

			for (JSONObject jsonObj : jsonObjectList) {

				for (Object key : jsonObj.keySet()) {

					String keyStr = (String) key;
					JSONObject keyvalue = (JSONObject) jsonObj.get(keyStr);
					String code = (String) keyvalue.get("Product Code");

					if (inputCodes.get(i).equals(code)) {
						String name = (String) keyvalue.get("Name");
						String price = (String) keyvalue.get("Price");
						itemList.add(new Product(code, name, price));
					}
				}
			}
		}
			
				
		for (Product item : itemList) {
			scan(item);
		}

		Double price = total();
		logger.debug("Total price expected: £" + price);

		return price;

	}

	public Double total() {
					
			salePrice = calculateTravelCardDiscount(getOriginalPrice());
			salePrice = apply10PercentDiscount(salePrice);

		return salePrice;
	}

	private Double apply10PercentDiscount(Double originalPrice) {
		if (originalPrice > TEN_DIS_AMT) {

			// Applying strategy pattern
			PromotionalRules promotionalRules = applyPromotion(new PercentPromotionStrategy(), "10percent");
			
			Double discount = originalPrice * promotionalRules.getDiscount();
			return originalPrice - discount;
		} else {
			
			return originalPrice;
		}

	}

	private Double calculateTravelCardDiscount(Double originalPrice) {
		int travelCardCount = 0;
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getProductCode().equals("001")) {
				travelCardCount++;
			}
		}

		if (travelCardCount >= TRAVAL_DIS_COUNT) {
			// Applying strategy pattern
			PromotionalRules promotionalRules =  applyPromotion(new TravelCardPromotionStrategy(), "travelcard");
			salePrice = (originalPrice - (promotionalRules.getDiscountAmount() * travelCardCount));

			return salePrice;
			
		} else {

			return originalPrice;
		}

	}

	public PromotionalRules applyPromotion(PromotionalRulesStrategy promotionalRulesStrategy, String ruleDefinition) {
		
		return promotionalRulesStrategy.applyPromotionalRule(ruleDefinition);
	}
	
}
