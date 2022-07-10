package com.techelevator.vendingmachine.inventory;

import java.util.Map;

public interface Inventory {
	void restock();
	
	Map<String, Slot> getInventory();
}
