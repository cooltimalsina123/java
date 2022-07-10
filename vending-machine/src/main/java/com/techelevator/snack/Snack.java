package com.techelevator.snack;

import java.math.BigDecimal;

import com.techelevator.MoneyFormatter;

public abstract class Snack {
	private final String name;
	private final BigDecimal price;

	public Snack(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}

	/**
	 * Get the sound that the Snack makes when consumed.
	 * 
	 * @return The sound that the Snack makes when consumed.
	 */
	public abstract String getSound();

	/**
	 * Get the name of the Snack.
	 * 
	 * @return The name of the Snack.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the price of the Snack.
	 * 
	 * @return The price of the Snack.
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Factory method for creating a Snack object.
	 * 
	 * @param snackType  The type of Snack to create.
	 * @param snackName  The name of the Snack.
	 * @param snackPrice The price of the Snack.
	 * @return A Snack.
	 */
	public static Snack createSnackForType(String snackType, String snackName, BigDecimal snackPrice) {
		if (snackType != null) {
			switch (snackType) {
			case "Candy":
				return new Candy(snackName, snackPrice);
			case "Chip":
				return new Chip(snackName, snackPrice);
			case "Drink":
				return new Drink(snackName, snackPrice);
			case "Gum":
				return new Gum(snackName, snackPrice);
			}
		}

		throw new IllegalArgumentException("No snack exists for snack type: " + snackType);
	}

	@Override
	public String toString() {
		return getSound() + System.lineSeparator() + "Name: " + getName() + ", Cost: " + MoneyFormatter.formatMoney(getPrice());
	}
}
