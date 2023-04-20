package org.example.domain.member;

import lombok.*;

import java.util.Collections;
import java.util.List;

@Data
public class Member {
    private Long memberId;
    private String name;
    private String personalNumber;
    private List<Long> accountIds;

    public Member(Long memberId, String name, String personalNumber) {
        this.memberId = memberId;
        this.name = name;
        this.personalNumber = personalNumber;
        this.accountIds = Collections.emptyList();
    }

    public boolean hasAccount(Long accountId) {
        return accountIds.contains(accountId);
    }


}
