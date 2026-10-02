package com.hamdi.car;


import java.util.UUID;

public class CarDao {
    public boolean addCar(Car car){
        if(!carExists(car)){//if car doesn't exist, add to array and increment User.lastIndex
            Car.cars[Car.lastIndex] = car;
            ++Car.lastIndex;
            return true;
        }
        return false;
    }

    public Car getCar(UUID id){
        for (Car car : Car.cars) {
            if(car.getId().equals(id)){
                return car;
            }
        }
        return null;
    }

    public Car[] getAllCars(){
        Car[] cars = Car.cars;
        return cars;
    }

    private boolean carExists(Car car){
        for (Car x : Car.cars) {
            if(x.equals(car)){
                return true;
            }
        }
        return false;
    }
}
