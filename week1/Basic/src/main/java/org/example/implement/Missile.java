package org.example.implement;

public interface Missile {
    default void countDown(){
        System.out.println("3..2..1..!!");
    }
    void launch();

    void explode();
}
