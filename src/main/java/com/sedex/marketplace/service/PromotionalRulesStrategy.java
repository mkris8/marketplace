package com.sedex.marketplace.service;

import com.sedex.marketplace.model.PromotionalRules;

public interface PromotionalRulesStrategy {
	public PromotionalRules applyPromotionalRule(String ruleDefinition);

}
