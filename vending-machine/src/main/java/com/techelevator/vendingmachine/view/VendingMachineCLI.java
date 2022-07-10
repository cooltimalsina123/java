package com.techelevator.vendingmachine.view;

import com.techelevator.vendingmachine.VendingMachine;
import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";

	private static final String[] MAIN_MENU_OPTIONS = 
		{ 
			MAIN_MENU_OPTION_DISPLAY_ITEMS, 
			MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT 
		};

	private final Menu menu;
	private final PurchaseMenuCLI purchaseMenuCLI;
	private final VendingMachine vendingMachine;

	public VendingMachineCLI(Menu menu, PurchaseMenuCLI purchaseMenuCLI, VendingMachine vendingMachine) {
		this.menu = menu;
		this.purchaseMenuCLI = purchaseMenuCLI;
		this.vendingMachine = vendingMachine;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			switch (choice) {
			case MAIN_MENU_OPTION_DISPLAY_ITEMS:
				System.out.println(vendingMachine);
				break;
			case MAIN_MENU_OPTION_PURCHASE:
				this.purchaseMenuCLI.run();
				break;
			case MAIN_MENU_OPTION_EXIT:
				System.out.println("Thanks for stopping by!");
				return;
			}
		}
	}
}
