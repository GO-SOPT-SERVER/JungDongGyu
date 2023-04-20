package org.example.domain.transaction;

import org.example.domain.util.transaction.DefaultTransaction;
import org.example.domain.util.transaction.Transaction;
import org.example.domain.util.transaction.TransactionType;


public class Deposit extends DefaultTransaction {
    public Deposit(String accountNumber, String type, Long amount) {
        super(accountNumber, type, amount);
    }

    @Override
    public String toString() {
        return "| " + getTime() + " | [" + getType() + "] " + getAccountNumber() + " | " + getAmount()+"원";
    }

}
