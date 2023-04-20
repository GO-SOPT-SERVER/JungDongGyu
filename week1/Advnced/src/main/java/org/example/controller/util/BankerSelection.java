package org.example.controller.util;

public enum BankerSelection {
    REGISTER("1"),
    NEW_ACCOUNT("2"),
    STOP("3");

    private final String selection;

    BankerSelection(String selection) {
        this.selection = selection;
    }

    public String selection() {
        return selection;
    }
}
