package fa.training.main;

import fa.training.entities.Car;
import fa.training.entities.Ford;
import fa.training.entities.Sedan;
import fa.training.entities.Truck;

import java.util.Arrays;

public class MyOwnAutoShop {
    public static void main(String[] args) {
        Sedan sedan1 = new Sedan(120, 1200000, "black", 19);
        Sedan sedan2 = new Sedan(150, 1500000, "white", 22);
        Sedan sedan3 = new Sedan(90, 100000, "red", 18);

        Ford ford1 = new Ford(120, 1200000, "black", 2016,20);
        Ford ford2 = new Ford(130, 1800000, "green", 2017,15);

        Truck truck1 = new Truck(120, 1200000, "black", 1560);
        Truck truck2 = new Truck(180, 2200000, "blue", 2210);

        Car[] cars = new Car[7];
        cars[0] = truck1;
        cars[1] = truck2;
        cars[2] = sedan1;
        cars[3] = sedan2;
        cars[4] = sedan3;
        cars[5] = ford1;
        cars[6] = ford2;

        Arrays.stream(cars).forEach(car -> System.out.println(car.toString()));
        
    }
}
