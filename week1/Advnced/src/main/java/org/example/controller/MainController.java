package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.controller.util.Controller;
import org.example.controller.util.MainSelection;
import org.example.controller.util.SelectionValidator;
import org.example.util.Request;
import org.example.view.In;
import org.example.view.Out;

import java.util.Arrays;


@NoArgsConstructor
public class MainController implements Controller {
    private final BankerController bankerController = new BankerController();
    private final MemberController memberController = new MemberController();
    private final SelectionValidator selectionValidator = new SelectionValidator();
    @Override
    public void run() {
        String selection ;
        do {
            selection = selectRole();
            if (MainSelection.BANKER.selection().equals(selection)) {
                bankerController.run();
            } else if (MainSelection.MEMBER.selection().equals(selection)) {
                memberController.run();
            }
        } while (!MainSelection.STOP.selection().equals(selection));
    }

    private String selectRole() {
        String selection ;
        do {
            Out.print(Request.REQUEST_ROLE);
            selection = In.read();
        } while (!checkIsAvailable(selection));
        return selection;
    }


    private boolean checkIsAvailable(String selection) {
        try {
            selectionValidator.validateMainSelection(selection);
            return true;
        } catch (IllegalArgumentException e) {
            Out.print(e.getMessage());
            return false;
        }
    }
}
