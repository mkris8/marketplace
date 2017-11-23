package com.sedex.marketplace.service;

import com.sedex.marketplace.model.PromotionalRules;

public class PercentPromotionStrategy implements PromotionalRulesStrategy {

	private PromotionalRules promotionalRules;
	
	@Override
	public PromotionalRules applyPromotionalRule(String ruleDefinition) {
		
		promotionalRules = new PromotionalRules();
	
		this.promotionalRules.setRuleDefinition(ruleDefinition);

			if (validateRuleDefinition(ruleDefinition)) {
				// spend over £60, then you get 10% off your purchase.
				if (ruleDefinition.equals("10percent")) {
					this.promotionalRules.setDiscount(0.10);
				}
		}
		
			return promotionalRules;
	}
	
	private boolean validateRuleDefinition(String ruleDefinition) {
		return ruleDefinition != null && !ruleDefinition.isEmpty();
	}

}
