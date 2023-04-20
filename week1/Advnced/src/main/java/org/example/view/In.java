package org.example.view;

import java.util.Scanner;

public class In {
    private In() {
    }
    private static final Scanner SC = new Scanner(System.in);
    public static String read() {
        String result = SC.nextLine();
        return result.trim();
    }
}
