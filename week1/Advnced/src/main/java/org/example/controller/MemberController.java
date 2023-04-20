package org.example.controller;

import org.example.controller.util.Controller;
import org.example.controller.util.MainSelection;
import org.example.controller.util.MemberSelection;
import org.example.controller.util.SelectionValidator;
import org.example.service.AccountService;
import org.example.service.MemberService;
import org.example.util.Command;
import org.example.util.Request;
import org.example.view.In;
import org.example.view.Out;

public class MemberController implements Controller {
    private final SelectionValidator selectionValidator = new SelectionValidator();
    private final MemberService memberService = new MemberService();
    private final AccountService accountService = new AccountService();

    @Override

    public void run() {
        // TODO 정상종료 : 해당 단계까지 돌아오게끔
        String selection;
        do {
            selection = selectFeature();
            executeFeature(selection);
        } while (!MemberSelection.STOP.selection().equals(selection));

    }
    private String selectFeature() {
        String selection;
        do {
            Out.print(Command.MEMBER_SERVICE);
            selection = In.read();
        } while (!checkIsAvailable(selection));
        return selection;
    }

    private boolean checkIsAvailable(String selection) {
        try {
            selectionValidator.validateMemberSelection(selection);
            return true;
        } catch (IllegalArgumentException e) {
            Out.print(e.getMessage());
            return false;
        }
    }

    private void executeFeature(String selection) {
        if (MemberSelection.DEPOSIT.selection().equals(selection)) {
            deposit();
        } else if (MemberSelection.WITHDRAW.selection().equals(selection)) {
            withdraw();
        } else if (MemberSelection.REMITTANCE.selection().equals(selection)) {
            send();
        } else if (MemberSelection.TRANSACTIONS.selection().equals(selection)) {
            viewTransactions();
        }
    }

    private void deposit() {

    }
    private void withdraw() {

    }
    private void send() {

    }
    private void viewTransactions() {

    }

}
