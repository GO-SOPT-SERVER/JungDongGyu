package org.example.repository;

import org.example.domain.member.Member;
import org.example.domain.util.member.MemberErrorMessage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class MemberRepository {
    private static final Map<Long, Member> members = new HashMap<>();
    private static Long sequence = 0L;
    private MemberRepository() {
    }
    // read-only
    public static Map<Long, Member> members() {
        return Collections.unmodifiableMap(members);
    }

    // add
    // Member 객체에 추가할 sequence 반환
    public static Member registerMember(String name, String personalNumber) {
        checkRegisterBefore(personalNumber);

        Member createdMember = new Member(++sequence, name, personalNumber);
        members.put(createdMember.getMemberId(), createdMember);
        return createdMember ;
    }

    public static Member findMemberByNameAndPN(String name, String personalNumber) {
//        isAbleToMakeAccount(name, personalNumber);
        return members.values().stream()
                .filter(member
                        -> member.getName().equals(name)
                        && member.getPersonalNumber().equals(personalNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MemberErrorMessage.NOT_FOUND));
    }

//    public static void patchMember(Member member) {
//        members.replace(member.getMemberId(), member);
//    }
//


    private static void checkRegisterBefore(String personalNumber) {
        if (isMemberExist(personalNumber)) {
            throw new IllegalArgumentException(MemberErrorMessage.ALREADY_EXIST);
        }
    }



    private static boolean isMemberExist(String personalNumber) {
        return members.values().stream()
                .anyMatch(member
                        -> member.getPersonalNumber().equals(personalNumber));
    }
}
