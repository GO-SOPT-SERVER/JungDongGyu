package sopt.week2.domain.repository;

import org.springframework.stereotype.Repository;
import sopt.week2.controller.dto.request.MemberRequestDto;
import sopt.week2.domain.Member;

import java.util.*;

@Repository
public class MemberRepository {
    private final List<Member> members = new ArrayList<>();
    private Long sequence = 0L;

    public Member saveMember(Member member) {
        member.setMemberId(++sequence);
        members.add(member);
        return member;
    }

    public Member updateMember(Member updateMember) {
        members.replaceAll(member -> member.getMemberId().equals(updateMember.getMemberId()) ?  updateMember : member);
        return updateMember;
    }
    public boolean isMemberExist(Long memberId) {
        return members.stream()
                .anyMatch(member -> Objects.equals(member.getMemberId(), memberId));
    }

    public Member findMemberById(Long memberId) {
        return members.stream()
                .filter(member -> Objects.equals(member.getMemberId(), memberId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    public List<Member> viewAllMembers() {
        if (members.size() == 0) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(members);
    }

    public void deleteMemberById(Long memberId) {
        boolean isDeleted = members.removeIf(member -> Objects.equals(member.getMemberId(), memberId));
        if (!isDeleted) {
            throw new IllegalArgumentException("존재하지 않는 회원이므로 삭제되지 않습니다.");
        }
    }
}
