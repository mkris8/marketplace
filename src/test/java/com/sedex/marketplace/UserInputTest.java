package com.sedex.marketplace;

import static org.junit.Assert.assertNotNull;

import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sedex.marketplace.model.PromotionalRules;
import com.sedex.marketplace.service.Checkout;
import com.sedex.marketplace.service.CheckoutService;

public class UserInputTest {
	private CheckoutService co ;
	private PromotionalRules promotionalRules;

	private static Logger logger = LoggerFactory.getLogger(UserInputTest.class);
	
	@Before
	public void setUp() throws Exception {
		PropertyConfigurator.configure("log4j.properties");
		
		promotionalRules = new PromotionalRules();
		co = new Checkout(promotionalRules);
	}
	
	@Test
	public void input() {
		
		promotionalRules = new PromotionalRules();
		co = new Checkout(promotionalRules);
		
		Scanner scanner = new Scanner(System.in);
	    logger.info("Basket Items: ");
	    String input = scanner.next();
	    scanner.close();
	    
		co.accept(input);
		assertNotNull(co.getItems());
	}

}
