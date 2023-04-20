package org.example.controller.util;

public enum MainSelection {
//    public static final String BANKER = "1";
//    public static final String MEMBER = "2";
//    public static final String STOP = "0";
    BANKER("1"),
    MEMBER("2"),
    STOP("0");

    private final String selection;

    MainSelection(String selection) {
        this.selection = selection;
    }

    public String selection() {
        return selection;
    }
}
