package org.example.domain.account;

import lombok.*;

@Data
public class Account {
    private Long memberId;
    private String accountNumber;
    private String password;
    private Long deposit;

    public Account(Long memberId, String accountNumber, String password) {
        this.memberId = memberId;
        this.accountNumber = accountNumber;
        this.password = password;
        this.deposit = 0L;
    }

    public Long increase(Long amount) {
        deposit = deposit + amount;
        return deposit;
    }
    public Long decrease(Long amount) {
        checkBalance(amount);
        deposit = deposit - amount;
        return deposit;
    }

    private void checkBalance(Long amount) {
        if (deposit - amount < 0) {
            throw new IllegalArgumentException("");
        }
    }

    public void checkPassword(String password) {
        if (!this.password.equals(password)) {
            throw new IllegalArgumentException();
        }
    }

}
