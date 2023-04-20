package org.example.domain.transaction;

import org.example.domain.util.transaction.DefaultTransaction;
import org.example.domain.util.transaction.Transaction;
import org.example.domain.util.transaction.TransactionType;

public class Remittance extends DefaultTransaction {
    private final String targetAccountNumber;

    public Remittance(String accountNumber, String type, Long amount, String targetAccountNumber) {
        super(accountNumber, type, amount);
        this.targetAccountNumber = targetAccountNumber;
    }

    @Override
    public String toString() {
        return "| " + getTime() + " | [" + getType() + "] " + getAccountNumber() + " → " + this.targetAccountNumber + " | " + getAmount()+"원";
    }
}
