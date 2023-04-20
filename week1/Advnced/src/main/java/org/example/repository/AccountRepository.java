package org.example.repository;

import org.example.domain.account.Account;
import org.example.domain.util.account.AccountErrorMessage;
import org.example.domain.util.member.MemberErrorMessage;
import org.example.repository.util.NumberCreator;

import java.util.*;

public class AccountRepository {
    private static final NumberCreator numberCreator = new NumberCreator();

    private static final Set<String> existNumbers = new HashSet<>();
    private static final Map<String, Account> accounts = new HashMap<>();

    // static final로 Repository 사용하기 위해
    // 생성자 private으로 막아두기
    private AccountRepository() {
    }

    // read-only
    public static Map<String, Account> accounts() {
        return Collections.unmodifiableMap(accounts);
    }

    // add
    // Member 객체에 추가할 sequence 반환
    public static String createAccountOf(Long memberId, String password) {
        String accountNumber = getUniqueAccountNumber();
        existNumbers.add(accountNumber);

//        accounts.put(accountNumber, new Account(memberId, accountNumber, password));
        return accountNumber ;
    }
    private static String getUniqueAccountNumber(){
        String randomNumber;
        do {
            randomNumber = numberCreator.makeRandomAccountNumber();
        } while (existNumbers.contains(randomNumber));
        return randomNumber;
    }

    // 출금 & 입금
    public static Long increaseDepositOf(String accountNumber, Long amount) {
        Account account = findAccountByNumber(accountNumber);
        Long deposit = account.increase(amount);
        accounts.replace(accountNumber, account);
        return deposit;
    }

    public static Long decreaseDepositOf(String accountNumber, Long amount) {
        Account account = findAccountByNumber(accountNumber);
        Long deposit = account.decrease(amount);
        accounts.replace(accountNumber, account);
        return deposit;
    }


    // find
    public static Account findAccountByNumber(String accountNumber) {
        isAccountExist(accountNumber);
        return accounts.get(accountNumber);
    }

    private static void isAccountExist(String accountNumber) {
        accounts().entrySet().stream()
                .filter(entry -> entry.getKey().equals(accountNumber))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException(AccountErrorMessage.NOT_FOUND));
    }
}
