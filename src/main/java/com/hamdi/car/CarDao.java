package com.hamdi.car;


import com.hamdi.user.User;
import com.hamdi.utility.ArrayHelper;

import java.math.BigDecimal;
import java.util.UUID;

public class CarDao {
    private static Car[] cars;
    public static int lastIndex;
    private static ArrayHelper arrayHelper = new ArrayHelper();

    static {
        cars = new Car[1];
        lastIndex = 0;
        cars = arrayHelper.add(cars,new Car(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),"J88H6",new BigDecimal("22.5"),Brand.TESLA,true),lastIndex++);
        cars = arrayHelper.add(cars,new Car(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),"EER88R",new BigDecimal("20.45"),Brand.TOYOTA,false),lastIndex++);
    }
    public boolean addCar(Car car){
        if(!carExists(car)){//if car doesn't exist, add to array and increment User.lastIndex
            cars = arrayHelper.add(cars,car,lastIndex++);
            return true;
        }
        return false;
    }

    public Car findCarById(UUID id){
        for (Car car : cars) {
            if(car.getId().equals(id)){
                return car;
            }
        }
        return null;
    }

    public Car[] getCars(){
        Car[] result = new Car[lastIndex];
        for (int i = 0; i < lastIndex; i++) {
            result[i] = cars[i];
        }
        return result;
    }

    private boolean carExists(Car car){
        for (Car x : getCars()) {
            if(x.equals(car)){
                return true;
            }
        }
        return false;
    }
}
