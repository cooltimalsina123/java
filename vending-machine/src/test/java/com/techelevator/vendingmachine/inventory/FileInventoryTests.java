package com.techelevator.vendingmachine.inventory;

import java.io.File;
import java.util.Map;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileInventoryTests {
	private final File inputFile = new File("vendingmachine.csv");
	
	@Test
	public void construct_has_empty_inventory() {
		// Arrange
		FileInventory fileInventory = new FileInventory(inputFile);

		// Act
		Map<String, Slot> inventory = fileInventory.getInventory();

		// Assert
		Assert.assertEquals(0, inventory.size());
	}

	@Test
	public void restock_with_invalid_file_returns_empty_stock() {
		// Arrange
		final File fakeFile = new File("bogus.csv");

		// Act
		FileInventory fileInventory = new FileInventory(fakeFile);

		// Assert
		Assert.assertEquals(0, fileInventory.getInventory().size());
	}
	
	@Test
	public void restock_adds_sixteen_slots() {
		// Arrange
		FileInventory fileInventory = new FileInventory(inputFile);

		// Act
		fileInventory.restock();
		Map<String, Slot> inventory = fileInventory.getInventory();

		// Assert
		Assert.assertEquals(16, inventory.size());
		
		Assert.assertTrue(inventory.containsKey("A1"));
		Assert.assertTrue(inventory.containsKey("A2"));
		Assert.assertTrue(inventory.containsKey("A3"));
		Assert.assertTrue(inventory.containsKey("A4"));
		
		Assert.assertTrue(inventory.containsKey("B1"));
		Assert.assertTrue(inventory.containsKey("B2"));
		Assert.assertTrue(inventory.containsKey("B3"));
		Assert.assertTrue(inventory.containsKey("B4"));
		
		Assert.assertTrue(inventory.containsKey("C1"));
		Assert.assertTrue(inventory.containsKey("C2"));
		Assert.assertTrue(inventory.containsKey("C3"));
		Assert.assertTrue(inventory.containsKey("C4"));
		
		Assert.assertTrue(inventory.containsKey("D1"));
		Assert.assertTrue(inventory.containsKey("D2"));
		Assert.assertTrue(inventory.containsKey("D3"));
		Assert.assertTrue(inventory.containsKey("D4"));
	}
	
	@Test
	public void restock_adds_default_amount_of_items_per_slot() {
		// Arrange
		FileInventory fileInventory = new FileInventory(inputFile);

		// Act
		fileInventory.restock();
		Map<String, Slot> inventory = fileInventory.getInventory();

		// Assert
		Assert.assertEquals(5, inventory.get("A1").getQuantity());
		Assert.assertEquals(5, inventory.get("A2").getQuantity());
		Assert.assertEquals(5, inventory.get("A3").getQuantity());
		Assert.assertEquals(5, inventory.get("A4").getQuantity());
		
		Assert.assertEquals(5, inventory.get("B1").getQuantity());
		Assert.assertEquals(5, inventory.get("B2").getQuantity());
		Assert.assertEquals(5, inventory.get("B3").getQuantity());
		Assert.assertEquals(5, inventory.get("B4").getQuantity());
		
		Assert.assertEquals(5, inventory.get("C1").getQuantity());
		Assert.assertEquals(5, inventory.get("C2").getQuantity());
		Assert.assertEquals(5, inventory.get("C3").getQuantity());
		Assert.assertEquals(5, inventory.get("C4").getQuantity());
		
		Assert.assertEquals(5, inventory.get("D1").getQuantity());
		Assert.assertEquals(5, inventory.get("D2").getQuantity());
		Assert.assertEquals(5, inventory.get("D3").getQuantity());
		Assert.assertEquals(5, inventory.get("D4").getQuantity());
	}
}
