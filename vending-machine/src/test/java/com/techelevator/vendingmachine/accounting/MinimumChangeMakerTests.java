package com.techelevator.vendingmachine.accounting;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MinimumChangeMakerTests {
	
	@Test(expected = IllegalArgumentException.class)
	public void getChangeFor_throws_exception_for_negative_amount() {
		// Arrange
		MinimumChangeMaker changeMaker = new MinimumChangeMaker();
		
		// Act
		changeMaker.getChangeFor(BigDecimal.valueOf(-1));
		
	}
	
	@Test
	public void getChangeFor_returns_zero_change() {
		// Arrange
		MinimumChangeMaker changeMaker = new MinimumChangeMaker();
		
		// Act
		Change change = changeMaker.getChangeFor(BigDecimal.ZERO);
		
		// Assert
		Assert.assertEquals(0, change.getQuarters());
		Assert.assertEquals(0, change.getDimes());
		Assert.assertEquals(0, change.getNickels());
		Assert.assertEquals(0, change.getPennies());
	}
	
	@Test
	public void getChangeFor_1_point_23_returns_mostly_quarters_then_dimes_then_nickels_then_pennies() {
		// Arrange
		MinimumChangeMaker changeMaker = new MinimumChangeMaker();
		
		// Act
		Change change = changeMaker.getChangeFor(BigDecimal.valueOf(1.23));
		
		// Assert
		Assert.assertEquals(4, change.getQuarters());
		Assert.assertEquals(2, change.getDimes());
		Assert.assertEquals(0, change.getNickels());
		Assert.assertEquals(3, change.getPennies());
	}
	
	@Test
	public void getChangeFor_point05_returns_one_nickel() {
		// Arrange
		MinimumChangeMaker changeMaker = new MinimumChangeMaker();
		
		// Act
		Change change = changeMaker.getChangeFor(BigDecimal.valueOf(.05));
		
		// Assert
		Assert.assertEquals(0, change.getQuarters());
		Assert.assertEquals(0, change.getDimes());
		Assert.assertEquals(1, change.getNickels());
		Assert.assertEquals(0, change.getPennies());
	}
	
	@Test
	public void getChangeFor_point099_rounds_up_and_returns_one_dime() {
		// Arrange
		MinimumChangeMaker changeMaker = new MinimumChangeMaker();
		
		// Act
		Change change = changeMaker.getChangeFor(BigDecimal.valueOf(.099));
		
		// Assert
		Assert.assertEquals(0, change.getQuarters());
		Assert.assertEquals(1, change.getDimes());
		Assert.assertEquals(0, change.getNickels());
		Assert.assertEquals(0, change.getPennies());
	}
}
