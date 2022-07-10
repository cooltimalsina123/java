package com.techelevator.snack;

import java.math.BigDecimal;

public class Gum extends Snack {
	public Gum(String name, BigDecimal price) {
		super(name, price);
	}

	@Override
	public String getSound() {
		return "Chew Chew, Yum!";
	}
}
