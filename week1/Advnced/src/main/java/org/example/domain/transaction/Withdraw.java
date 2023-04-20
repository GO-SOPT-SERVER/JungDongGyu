package org.example.domain.transaction;

import org.example.domain.util.transaction.DefaultTransaction;
import org.example.domain.util.transaction.Transaction;

public class Withdraw extends DefaultTransaction {
    public Withdraw(String accountNumber, String type, Long amount) {
        super(accountNumber, type, amount);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
