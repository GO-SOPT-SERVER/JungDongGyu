package org.example.repository.util;

import java.util.Random;

public class NumberCreator {
    private static final int LIMIT = 6;
    private static final Random random = new Random();


    public String makeRandomAccountNumber(){
        int createNum ;
        StringBuilder resultNum = new StringBuilder();
        for (int i = 0 ; i < LIMIT; i++ ){
            createNum = random.nextInt(10);
            resultNum.append(createNum);
        }
        return resultNum.toString();
    }
}
