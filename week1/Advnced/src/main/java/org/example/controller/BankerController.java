package org.example.controller;

import org.example.controller.util.BankerSelection;
import org.example.controller.util.Controller;
import org.example.controller.util.MemberSelection;
import org.example.controller.util.SelectionValidator;
import org.example.domain.member.Member;
import org.example.service.AccountService;
import org.example.service.MemberService;
import org.example.util.Command;
import org.example.util.Request;
import org.example.view.In;
import org.example.view.Out;

public class BankerController implements Controller {
    private final SelectionValidator selectionValidator = new SelectionValidator();
    private final MemberService memberService = new MemberService();
    private final AccountService accountService = new AccountService();
    @Override
    public void run() {
        String selection;
        do {
            selection = selectFeature();
            executeFeature(selection);
        } while (!BankerSelection.STOP.selection().equals(selection));
    }

    private String selectFeature() {
        String selection;
        do {
            Out.print(Command.BANKER_SERVICE);
            selection = In.read();
        } while (!checkIsAvailable(selection));
        return selection;
    }

    private boolean checkIsAvailable(String selection) {
        try {
            selectionValidator.validateBankerSelection(selection);
            return true;
        } catch (IllegalArgumentException e) {
            Out.print(e.getMessage());
            return false;
        }
    }

    private void executeFeature(String selection) {
        try {
            if (BankerSelection.REGISTER.selection().equals(selection)) {
                register();
            } else if (BankerSelection.NEW_ACCOUNT.selection().equals(selection)) {
                createAccount();
            }
        } catch (IllegalArgumentException e) {
            Out.print(e.getMessage());
        }
    }
    private void register() {
        Out.print(Request.REQUEST_NAME);
        String name = In.read();
        Out.print(Request.REQUEST_PN);
        String personalNumber = In.read();
        memberService.registerMember(name, personalNumber);
    }
    private void createAccount() {
        Out.print(Request.REQUEST_NAME);
        String name = In.read();
        Out.print(Request.REQUEST_PN);
        String personalNumber = In.read();

        Member member = memberService.findMemberByNameAndPN(name, personalNumber);
        Out.print(member);
        Out.print(Request.REQUEST_PASSWORD);
        String accountPassword = In.read();
        Out.print(accountPassword);
        String accountNumber = accountService.createAccountOf(member.getMemberId(), accountPassword);
        Out.print("발급된 계좌번호는 \"" + accountNumber + "\" 입니다.");
    }
}
