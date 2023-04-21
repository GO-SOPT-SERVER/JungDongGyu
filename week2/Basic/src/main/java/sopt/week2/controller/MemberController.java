package sopt.week2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sopt.week2.controller.dto.request.BoardRequestDto;
import sopt.week2.controller.dto.request.MemberRequestDto;
import sopt.week2.domain.Board;
import sopt.week2.domain.Member;
import sopt.week2.service.BoardService;
import sopt.week2.service.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/{memberId}")
    public ResponseEntity findMemberById(@PathVariable("memberId") Long memberId) {
        try {
            return ResponseEntity.ok(memberService.findMemberById(memberId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/member")
    public ResponseEntity postMember(
            @RequestBody MemberRequestDto memberRequestDto
    ) {
        try {
            Member postMember = memberService.saveMember(memberRequestDto);
            return ResponseEntity.ok(postMember);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }

    }

    @PutMapping("/member/{memberId}")
    public ResponseEntity createOrUpdate(
            @PathVariable("memberId") Long memberId
            , @RequestBody MemberRequestDto memberRequestDto
    ) {
        try {
            Member member;
            if (memberService.isMemberExist(memberId)) {
                member = memberService.updateMember(memberId, memberRequestDto);
            } else {
                member = memberService.saveMember(memberRequestDto);
            }
            return ResponseEntity.ok(member);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity viewMemberList() {
        List<Member> members = memberService.getAllMembers();
        if (members.isEmpty()) {
            return ResponseEntity.ok("작성된 게시물이 없습니다.");
        }
        return ResponseEntity.ok(members);
    }

    @DeleteMapping("/delete/{memberId}")
    public ResponseEntity deleteBoard(
            @PathVariable("memberId") Long memberId
    ){
        try {
            memberService.deleteMember(memberId);
            return ResponseEntity.ok("삭제가 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
