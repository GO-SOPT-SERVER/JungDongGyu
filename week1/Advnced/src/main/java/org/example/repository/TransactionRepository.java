package org.example.repository;

import org.example.domain.member.Member;
import org.example.domain.util.transaction.Transaction;

import java.util.*;
import java.util.stream.Collectors;

public class TransactionRepository {
    private static final List<Transaction> transactions = new ArrayList<>();
    private TransactionRepository() {
    }

    // read-only
    private static List<Transaction> transactions() {
        return Collections.unmodifiableList(transactions);
    }

    // add
    public static void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    // find
    public static Optional<List<Transaction>> searchTransactionsOf(String accountNumber) {
        return Optional.of(transactions().stream()
                .filter(transaction -> transaction.isTransactionOf(accountNumber))
                .collect(Collectors.toList()));
    }

}
