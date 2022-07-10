package com.techelevator.vendingmachine.inventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import com.techelevator.snack.Snack;

public class FileInventory implements Inventory {
	private static final int INITIAL_QUANTITY = 5;
	private final File inputFile;
	private Map<String, Slot> inventory = new LinkedHashMap<>();
	
	public FileInventory(File inputFile) {
		this.inputFile = inputFile;
	}
	
	@Override
	public void restock() {
		try (Scanner fileScanner = new Scanner(inputFile)) {
			int lineCounter = 0;
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				lineCounter++;
				String[] lineParts = line.split("\\|");

				if (lineParts.length < 4 || lineParts[2] == null) {
					throw new IllegalArgumentException("Invalid file format on line " + lineCounter);
				}

				String slotName = lineParts[0];
				String snackName = lineParts[1];
				double snackPrice = Double.parseDouble(lineParts[2]);
				String snackType = lineParts[3];

				Snack snack = Snack.createSnackForType(snackType, snackName, BigDecimal.valueOf(snackPrice));

				Slot theSlot;
				if (inventory.containsKey(slotName)) {
					theSlot = inventory.get(slotName);
				} else {
					theSlot = new Slot(slotName);
					inventory.put(slotName, theSlot);
				}

				// Add 5 of the Snacks to the Slot
				for (int i = 1; i <= INITIAL_QUANTITY; i++) {
					theSlot.addItem(snack);
				}
			}
		} catch (FileNotFoundException e) {
			inventory = new LinkedHashMap<>();
		}
	}

	@Override
	public Map<String, Slot> getInventory() {
		return new LinkedHashMap<>(inventory);
	}

}
