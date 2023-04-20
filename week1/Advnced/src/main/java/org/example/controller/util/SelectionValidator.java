package org.example.controller.util;

import org.example.util.SystemErrorMessage;

import java.util.Arrays;

public class SelectionValidator {

    public void validateMainSelection(String selection) {
        if (Arrays.stream(MainSelection.values())
                .noneMatch(var -> var.selection().equals(selection))) {
            throw new IllegalArgumentException(SystemErrorMessage.WRONG_SELECT);
        } else if (selection.isBlank() || selection.isEmpty()) {
            throw new IllegalArgumentException(SystemErrorMessage.INPUT_BLANK);
        }
    }
    public void validateBankerSelection(String selection) {
        if (Arrays.stream(BankerSelection.values())
                .noneMatch(var -> var.selection().equals(selection))) {
            throw new IllegalArgumentException(SystemErrorMessage.WRONG_SELECT);
        } else if (selection.isBlank() || selection.isEmpty()) {
            throw new IllegalArgumentException(SystemErrorMessage.INPUT_BLANK);
        }
    }
    public void validateMemberSelection(String selection) {
        if (Arrays.stream(MemberSelection.values())
                .noneMatch(var -> var.selection().equals(selection))) {
            throw new IllegalArgumentException(SystemErrorMessage.WRONG_SELECT);
        } else if (selection.isBlank() || selection.isEmpty()) {
            throw new IllegalArgumentException(SystemErrorMessage.INPUT_BLANK);
        }
    }

}
