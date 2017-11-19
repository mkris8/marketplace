package com.sedex.marketplace;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.sedex.marketplace.model.PromotionalRules;
import com.sedex.marketplace.model.json.Product;
import com.sedex.marketplace.service.Checkout;
import com.sedex.marketplace.service.CheckoutService;

public class OnlineMarketPlaceTest {

	private CheckoutService co ;
	private PromotionalRules promotionalRules;
	private Product item1;
	private Product item2;
	private Product item3;
	
	@Before
	public void setUp() throws Exception {
		promotionalRules = new PromotionalRules();
		co = new Checkout(promotionalRules);
		item1 = new Product("001","Travel Card Holder", "9.25");
		item2 = new Product("002", "Personalised cufflinks", "45.00");
		item3 = new Product("003", "Kids T-shirt", "19.95");
	}

	// Tests for user input formats
	@Test
	public void accept_1() {
		assertThat(co.accept("001,002,003::"), is(66.78));
	}
	
	@Test
	public void accept_2() {
		assertThat(co.accept("001,003,001 ::"), is(36.95));
	}
	
	@Test
	public void accept_3() {
		assertThat(co.accept("001,002,001,003 ::"), is(73.755));
	}

	@Test
	public void accept_4() {
		assertThat(co.accept("001::"), is(9.25));
	}
	
	@Test
	public void accept_5() {
		assertThat(co.accept("001,002::"), is(54.25));
	}
	
	// Tests for methods
	
	@Test
	public void scan() {
		co.scan(item1);
		co.scan(item2);
		co.scan(item3);
		
		assertNotNull(co.getItems());
		assertThat(co.getItems().size(),is(3));
		assertThat(co.getItems().get(0).getProductCode(), is("001"));
		assertThat(co.getItems().get(0).getName(), equalTo("Travel Card Holder"));
		assertThat(co.getItems().get(0).getPrice(), is("9.25"));
	}

	@Test
	public void calculateTotalWithoutPromotions() {
		co.scan(item1);
		co.scan(item2);
		co.scan(item3);
		
		assertNotNull(co.getItems());
		assertThat(co.getItems().size(),is(3));
		assertThat(co.getOriginalPrice(), is(74.2));
	}

	@Test
	public void calculateTotalWithoutPromotions2() {
		co.scan(item1);
		co.scan(item2);
		co.scan(item3);
		co.scan(item3);
		co.scan(item3);
		co.scan(item3);
		co.scan(item3);
		
		assertNotNull(co.getItems());
		assertThat(co.getItems().size(),is(7));
		assertThat(co.getOriginalPrice(), is(154.0));
	}

	@Test
	public void apply10PercentPromotionOver60_1() {
		promotionalRules = new PromotionalRules();
		promotionalRules.apply("10percent");
		co = new Checkout(promotionalRules);
		co.scan(item2);
		co.scan(item3);
		assertThat(co.total(), is(58.455));
		
	}
	
	@Test
	public void apply10PercentPromotionOver60_2() {
		promotionalRules = new PromotionalRules();
		promotionalRules.apply("10percent");
		co = new Checkout(promotionalRules);
		co.scan(item1);
		co.scan(item2);
		co.scan(item1);
		assertThat(co.total(), is(55.8));
	}
	
	@Test
	public void apply10PercentPromotionBelow60() {
		promotionalRules.apply("10percent");
		co = new Checkout(promotionalRules);
		co.scan(item2);
		assertThat(co.total(), is(45.0));
		
		promotionalRules.apply("10percent");
		co = new Checkout(promotionalRules);
		co.scan(item1);
		assertThat(co.total(), is(9.25));
	}
	
	@Test
	public void applyTravelCardPromotionForExactlyTwo() {
		promotionalRules.apply("travelcard");
		co = new Checkout(promotionalRules);
		co.scan(item1);
		co.scan(item1);
		assertThat(co.total(), is(17.00));
	}
	
	@Test
	public void applyTravelCardPromotionForTwoOrMore() {
		promotionalRules.apply("travelcard");
		co = new Checkout(promotionalRules);
		co.scan(item1);
		co.scan(item1);
		co.scan(item2);
		co.scan(item3);
		co.scan(item1);
		co.scan(item3);
		assertThat(co.total(), is(99.36));
	}
	
	@Test
	public void applyTravelCardPromotionForOne() {
		promotionalRules.apply("travelcard");
		co = new Checkout(promotionalRules);
		co.scan(item1);
		assertThat(co.total(), is(9.25));
	}
	
	@Test
	public void applyTravelCardPromotionForZero() {
		promotionalRules.apply("travelcard");
		co = new Checkout(promotionalRules);
		co.scan(item2);
		assertThat(co.total(), is(45.00));
	}
	
	@Test
	public void applyEmptyPromotion() {
		promotionalRules.apply("");
		co = new Checkout(promotionalRules);
		co.scan(item2);
		assertThat(co.total(), is(45.00));
	}
	
	@Test
	public void applyNoPromotions() {
		co = new Checkout(promotionalRules);
		co.scan(item2);
		assertThat(co.total(), is(45.00));
	}
	
	
}
