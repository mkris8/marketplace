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

	public void apply(String ruleDefinition) {
		this.ruleDefinition = ruleDefinition;

		if (validateRuleDefinition(ruleDefinition)) {
			// spend over £60, then you get 10% off your purchase.
			if (ruleDefinition.equals("10percent")) {
				this.discountRate = 0.10;
			}

			// buy 2 or more travel card holders then the price drops to £8.50
			if (ruleDefinition.equals("travelcard")) {
				this.discountAmount = 0.75;
			}
		}

	}

	private boolean validateRuleDefinition(String ruleDefinition) {
		return ruleDefinition != null && !ruleDefinition.isEmpty();
	}

}