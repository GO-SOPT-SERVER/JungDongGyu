package org.example.service;

import org.example.domain.member.Member;
import org.example.repository.MemberRepository;

public class MemberService {

    public void registerMember(String name, String personalNumber) {
        MemberRepository.registerMember(name, personalNumber);
    }

    public Member findMemberByNameAndPN(String name, String personalNuber) {
        return MemberRepository.findMemberByNameAndPN(name, personalNuber);
    }
}
