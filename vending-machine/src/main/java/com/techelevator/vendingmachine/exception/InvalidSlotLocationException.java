package com.techelevator.vendingmachine.exception;

@SuppressWarnings("serial")
public class InvalidSlotLocationException extends RuntimeException {
	public InvalidSlotLocationException(String message) {
		super(message);
	}
}
