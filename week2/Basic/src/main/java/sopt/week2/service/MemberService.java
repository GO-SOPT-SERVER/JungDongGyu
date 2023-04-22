package sopt.week2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.week2.controller.dto.request.BoardRequestDto;
import sopt.week2.controller.dto.request.MemberRequestDto;
import sopt.week2.domain.Board;
import sopt.week2.domain.Member;
import sopt.week2.domain.repository.MemberRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;



    public Member saveMember(MemberRequestDto memberRequestDto) {
        Member member = new Member();
        member.setName(memberRequestDto.getName());
        member.setAge(memberRequestDto.getAge());
        member.setPhoneNumber(memberRequestDto.getPhoneNumber());
        memberRepository.saveMember(member);
        return member;
    }

    public boolean isMemberExist(Long id) {
        return memberRepository.isMemberExist(id);
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findMemberById(memberId);
    }
    public List<Member> getAllMembers() {
        return memberRepository.viewAllMembers();
    }

    public Member updateMember(Long memberId, MemberRequestDto memberRequestDto) {
        Member updateMember = new Member();
        // 바뀌지 않아야하는 정보
        updateMember.setMemberId(memberId);

        // 바뀔 수 있는 정보 덮어쓰기
        updateMember.setAge(memberRequestDto.getAge());
        updateMember.setName(memberRequestDto.getName());
        updateMember.setPhoneNumber(memberRequestDto.getPhoneNumber());
        return memberRepository.updateMember(updateMember);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteMemberById(id);
    }
}
