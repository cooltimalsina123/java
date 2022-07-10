package com.techelevator.vendingmachine.inventory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;

import com.techelevator.MoneyFormatter;
import com.techelevator.snack.Snack;

public class Slot {
	private final Queue<Snack> items;
	private final String name;

	/**
	 * Create a named Slot. A Slot holds and distributes Snack items in a first in,
	 * first out manner
	 * 
	 * @param name The name for the slot.
	 */
	public Slot(String name) {
		this.name = name;
		this.items = new LinkedList<>();
	}

	/**
	 * Add a Snack item to the Slot.
	 * 
	 * @param snack Snack item to add.
	 */
	public void addItem(Snack snack) {
		items.offer(snack);
	}

	/**
	 * Vend a Snack item from the front of the Slot.
	 * 
	 * @return The Snack item that is vended.
	 */
	public Snack vend() {
		return items.poll();
	}

	/**
	 * Get the quantity of Snack items in the Slot.
	 * 
	 * @return the quantity of Snack items in the Slot.
	 */
	public int getQuantity() {
		return items.size();
	}

	/**
	 * Get the name of the Slot.
	 * 
	 * @return The name of the Slot;
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the price of the Snack at the front of the Slot.
	 * 
	 * @return The price of the Snack at the front of the Slot;
	 */
	public BigDecimal getPrice() {
		return items.peek().getPrice();
	}

	@Override
	public String toString() {
		if (getQuantity() <= 0) {
			return getName() + " Sold Out";
		}

		final String slotName = getName();
		final String itemName = items.peek().getName();
		final String price = MoneyFormatter.formatMoney(getPrice());
		final int quantity = getQuantity();
		
		return slotName + " " 
			+ itemName  + " " 
			+ price     + ", "
			+ quantity  + " remaining.";
	}
}
