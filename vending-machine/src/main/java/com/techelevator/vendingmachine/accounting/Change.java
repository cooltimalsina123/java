package com.techelevator.vendingmachine.accounting;

public class Change {
	private final int quarters;
	private final int dimes;
	private final int nickels;
	private final int pennies;
	
	public Change(int quarters, int dimes, int nickels, int pennies) {
		if(quarters < 0 || dimes < 0 || nickels < 0 || pennies < 0) {
			throw new IllegalArgumentException("Invalid amount of coins. You must supply a positive integer value.");
		}
		
		this.quarters = quarters;
		this.dimes = dimes;
		this.nickels = nickels;
		this.pennies = pennies;
	}
	
	public int getQuarters() {
		return quarters;
	}
	
	public int getDimes() {
		return dimes;
	}
	
	public int getNickels() {
		return nickels;
	}
	
	public int getPennies() {
		return pennies;
	}
	
	@Override
	public String toString() {
		if(quarters == 0 && dimes == 0 && nickels == 0 && pennies == 0) {
			return "No change";
		}
	
		StringBuilder changeString = new StringBuilder();
		
		if(quarters == 1) {
			changeString.append("1 quarter ");
		} else {
			changeString.append(quarters).append(" quarters ");
		}
		
		if(dimes == 1) {
			changeString.append("1 dime ");
		} else {
			changeString.append(dimes).append(" dimes ");
		}
		
		if(nickels == 1) {
			changeString.append("1 nickel ");
		} else {
			changeString.append(nickels).append(" nickels ");
		}
		
		if(pennies == 1) {
			changeString.append("1 penny ");
		} else {
			changeString.append(pennies).append(" pennies ");
		}
		
		return changeString.substring(0, changeString.length() - 1); // remove last space
	}
}
