package org.example.service;

import org.example.domain.account.Account;
import org.example.domain.member.Member;
import org.example.domain.transaction.Deposit;
import org.example.domain.transaction.Remittance;
import org.example.domain.transaction.Withdraw;
import org.example.domain.util.transaction.Transaction;
import org.example.domain.util.transaction.TransactionType;
import org.example.repository.AccountRepository;
import org.example.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

public class AccountService {

    public String createAccountOf(Long memberId, String password) {
        return AccountRepository.createAccountOf(memberId, password);
    }

    public Long checkBalanceOf(String accountNumber) {
        Account targetAmount = AccountRepository.findAccountByNumber(accountNumber);
        return targetAmount.getDeposit();
    }
    public void depositMoneyTo(String accountNumber, Long amount) {
        AccountRepository.increaseDepositOf(accountNumber, amount);
        TransactionRepository.addTransaction(new Deposit(accountNumber, TransactionType.IN, amount));
    }

    public void withdrawMoneyFrom(String accountNumber, Long amount) {
        AccountRepository.decreaseDepositOf(accountNumber, amount);
        TransactionRepository.addTransaction(new Withdraw(accountNumber, TransactionType.OUT, amount));
    }

    public void sendMoney(String from, String to, Long amount) {
        AccountRepository.decreaseDepositOf(from, amount);
        AccountRepository.increaseDepositOf(to, amount);
        TransactionRepository.addTransaction(new Remittance(from,TransactionType.IN, amount,to));
    }

    public Optional<List<Transaction>> viewTransactionsOf(String accountNumber) {
        return TransactionRepository.searchTransactionsOf(accountNumber);
    }
}
