package com.sedex.marketplace.model;

public class PromotionalRules {

	private String ruleDefinition;
	private Double discountRate = 0.00;
	private Double discountAmount = 0.00;

	public String getRuleDefinition() {
		return ruleDefinition;
	}

	public void setRuleDefinition(String ruleDefinition) {
		this.ruleDefinition = ruleDefinition;
	}

	public Double getDiscount() {
		return discountRate;
	}

	public void setDiscount(Double discount) {
		this.discountRate = discount;
	}

	public Double getDiscountAmount() {
		return this.discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

}