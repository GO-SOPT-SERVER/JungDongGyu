package org.example.extend;

public class commonExtend {
    public static void main(String[] args) {
        Car car1 = new Car();
        car1.setName("Normal");
        Car car2 = new SuperCar();
        car2.setName("람보르기니");
        SuperCar superCar = new SuperCar();
        superCar.setName("부가티");
        Car car3 = new Truck();
        car3.setName("포터");
        Truck truck = new Truck();
        truck.setName("볼보");


        car1.drive();

        car2.drive();
        superCar.drive();
        superCar.boost();

        car3.drive();
        truck.drive();
        truck.load();
        /*
        Normal 차가 달립니다.
        람보르기니슈퍼카가 달립니다.
        부가티슈퍼카가 달립니다.
        슈퍼카 부스터!! 마하 10
        포터트럭이 달립니다.
        볼보트럭이 달립니다.
        트럭 짐 싣기!! 10톤
         */

    }

    static class Car {
        String name;


        void setName(String name) {
            this.name = name;
        }

        void drive() {
            System.out.println(this.name + " 차가 달립니다.");
        }
    }

    static class SuperCar extends Car{
        private String maximumSpeed = "마하 10";


        @Override
        void drive() {
            System.out.println(this.name + "슈퍼카가 달립니다.");
        }
        void boost(){
            System.out.println("슈퍼카 부스터!! " + this.maximumSpeed);
        }
    }

    static class Truck extends Car {
        private String maximumWeight = "10톤";
        @Override
        void drive() {
            System.out.println(this.name + "트럭이 달립니다.");
        }
        void load() {
            System.out.println("트럭 짐 싣기!! "+maximumWeight);
        }
    }
}
