package org.example.extend;

public class abstractExtend {
    public static void main(String[] args) {
        // 객체 생성 불가능 -> abstract 클래스
//        Person person = new Person()
        Mathematician mathematician1 = new Mathematician("Kim");
        Scientist scientist1 = new Scientist("Jung");
        Person mathematician2 = new Mathematician("Choi");
        Person scientist2 = new Scientist("Park");

        System.out.println(mathematician1.work());
        System.out.println(mathematician2.work());

        System.out.println(mathematician1.think());
        System.out.println(mathematician2.think());
        // mathematician2 는 studyMath() 호출 불가능
        System.out.println(mathematician1.studyMath());

        System.out.println(scientist2.think());
        System.out.println(scientist1.studyScience());
    }


    abstract static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }
        public String work() {
            return this.name + "이 일합니다.";
        }

        abstract String think();
    }

    static class Mathematician extends Person {
        public Mathematician(String name) {
            super(name);
        }

        @Override
        String think() {
            return "수학만을 생각합니다.";
        }
        String studyMath() {
            return "수학 공부";
        }
    }

    static class Scientist extends Person {
        public Scientist(String name) {
            super(name);
        }

        @Override
        String think() {
            return "과학만을 생각합니다.";
        }
        String studyScience() {
            return "과학 공부";
        }
    }

}
