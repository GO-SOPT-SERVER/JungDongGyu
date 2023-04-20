package org.example.domain.util.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class DefaultTransaction implements Transaction{
    String accountNumber;
    String type;
    Long amount;

    String time;

    public DefaultTransaction(String accountNumber, String type, Long amount) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public boolean isTransactionOf(String accountNumber) {
        return this.accountNumber.equals(accountNumber);
    }
}
