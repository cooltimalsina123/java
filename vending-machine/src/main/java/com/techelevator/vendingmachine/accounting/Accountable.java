package com.techelevator.vendingmachine.accounting;

import java.math.BigDecimal;

public interface Accountable {
    /**
     * Get the remaining balance.
     *
     * @return The remaining balance.
     */
    BigDecimal getBalance();

    /**
     * Deposit money into the account's remaining balance.
     * @param amount The amount to deposit.
     * @return True if the amount was deposited. False otherwise.
     */
    boolean deposit(BigDecimal amount);

    /**
     * Withdraw money from the remaining balance.
     * @param amount The amount to withdraw.
     * @throws InsufficientFundsException When there is not enough balance to withdraw.
     */
    void withdraw(BigDecimal amount) throws InsufficientFundsException;

    /**
     * Refund the remaining balance and return it as Change.
     * @return Change representation of the refund.
     */
    Change refundBalance();
}
