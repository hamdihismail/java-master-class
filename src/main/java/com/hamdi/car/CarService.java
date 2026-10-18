package com.hamdi.car;


import com.hamdi.utility.ArrayHelper;

import java.util.UUID;

public class CarService {
    CarDao carDao = new CarDao();
    ArrayHelper arrayHelper = new ArrayHelper();
    public Car lookUp(UUID id) {
        try {
            return carDao.findCarById(id);
        } catch (Exception e){
            System.out.println("Car not found!\n");
            return null;
        }
    }

    public Car[] getElectric(){
        Car[] result = new Car[1];
        int lastIndex = 0;
        for (Car car : getAll()) {
            if((car != null) && car.isElectric()){
                if(lastIndex == 0){
                    result[0] = car;
                } else{
                    result = arrayHelper.add(result,car,lastIndex++);
                }
            }
        }
        return result;
    }

    public Car[] getAll(){
        return carDao.getCars();
    }
}
