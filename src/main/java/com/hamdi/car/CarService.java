package com.hamdi.car;


import java.util.UUID;

public class CarService {
    CarDao carDao = new CarDao();

    public Car lookUp(UUID id) {
        try {
            return carDao.getCar(id);
        } catch (Exception e){
            System.out.println("Car not found!\n");
            return null;
        }
    }

    public Car[] getAll(){
        return carDao.getAllCars();
    }
}
