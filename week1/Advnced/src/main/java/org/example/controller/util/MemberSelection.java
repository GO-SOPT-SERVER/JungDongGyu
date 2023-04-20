package org.example.controller.util;

public enum MemberSelection {
//    public static final String BANKER = "1";
//    public static final String MEMBER = "2";
//    public static final String STOP = "0";
    DEPOSIT("1"),
    WITHDRAW("2"),
    REMITTANCE("3"),
    TRANSACTIONS("4"),
    STOP("5");



    private final String selection;

    MemberSelection(String selection) {
        this.selection = selection;
    }

    public String selection() {
        return selection;
    }
}
