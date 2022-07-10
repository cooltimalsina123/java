package com.techelevator.vendingmachine.accounting;

import java.math.BigDecimal;

public class CashAccount implements Accountable {
    private BigDecimal balance = new BigDecimal(0);
    private final ChangeMaker changeMaker;

    public CashAccount(ChangeMaker changeMaker) {
        this.changeMaker = changeMaker;
    }

    /**
     * Get the remaining balance.
     *
     * @return The remaining balance.
     */
    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Deposit money into the account's remaining balance.
     * @param amount The amount to deposit.
     * @return True if the amount was deposited. False otherwise.
     */
    @Override
    public boolean deposit(BigDecimal amount) {
        if(!isValidAmount(amount)) return false;

        balance = balance.add(amount);
        return true;
    }

    private boolean isValidAmount(BigDecimal amount) {
        if (amount == null)
            return false;

        if (amount.compareTo(BigDecimal.valueOf(1)) == 0) { // 1 Dollar
            return true;
        } else if (amount.compareTo(BigDecimal.valueOf(2)) == 0) { // 2 Dollars
            return true;
        } else // 10 Dollars
            if (amount.compareTo(BigDecimal.valueOf(5)) == 0) { // 5 Dollars
            return true;
        } else return amount.compareTo(BigDecimal.valueOf(10)) == 0;
    }

    /**
     * Withdraw money from the remaining balance.
     * @param amount The amount to withdraw.
     * @throws InsufficientFundsException When there is not enough balance to withdraw.
     */
    @Override
    public void withdraw(BigDecimal amount) throws InsufficientFundsException {
        if (balance.compareTo(amount) < 0) throw new InsufficientFundsException();

        balance = balance.subtract(amount);
    }

    /**
     * Refund the remaining balance and return it as Change.
     * @return Change representation of the refund.
     */
    @Override
    public Change refundBalance() {
        Change change = this.changeMaker.getChangeFor(this.balance);
        this.balance = BigDecimal.ZERO;
        return change;
    }
}
