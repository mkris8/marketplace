package com.sedex.marketplace.service;

import com.sedex.marketplace.model.PromotionalRules;

public class TravelCardPromotionStrategy implements PromotionalRulesStrategy {

	private PromotionalRules promotionalRules;
	
	@Override
	public PromotionalRules applyPromotionalRule(String ruleDefinition) {
		
		promotionalRules = new PromotionalRules();
	
		this.promotionalRules.setRuleDefinition(ruleDefinition);

			if (validateRuleDefinition(ruleDefinition)) {
				// buy 2 or more travel card holders then the price drops to £8.50
				if (ruleDefinition.equals("travelcard")) {
					this.promotionalRules.setDiscountAmount(0.75);
				}
		}
			
			return promotionalRules;

	}

	private boolean validateRuleDefinition(String ruleDefinition) {
		return ruleDefinition != null && !ruleDefinition.isEmpty();
	}

}
