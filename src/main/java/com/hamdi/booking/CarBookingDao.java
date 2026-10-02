package com.hamdi.booking;


import com.hamdi.car.Car;

import java.util.UUID;

public class CarBookingDao {
    public boolean addCarBooking(CarBooking carBookings){
        if(!carBookingsExists(carBookings)){//if carBookings doesn't exist, add to array and increment CarBooking.lastIndex
            CarBooking.carBookings[CarBooking.lastIndex] = carBookings;
            ++CarBooking.lastIndex;
            return true;
        }
        return false;
    }

    public boolean deleteCarBooking(UUID id){
        for (int i = 0; i < CarBooking.carBookings.length; i++) {
           if((CarBooking.carBookings[i] != null) && (CarBooking.carBookings[i].getId().equals(id))){
               CarBooking.carBookings[i] = null;
               return true;
           }
        }
        return false;
    }

    public CarBooking getCarBooking(UUID id){
        for (CarBooking carBookings : CarBooking.carBookings) {
            if(carBookings.getId().equals(id)){
                return carBookings;
            }
        }
//        throw new Exception("CarBooking not found");
        return null;
    }
    public CarBooking[] getAllCarBookings(){
        CarBooking[] bookings = CarBooking.carBookings;
        return bookings;
    }
    private boolean carBookingsExists(CarBooking carBookings){
        for (CarBooking x : CarBooking.carBookings) {
            if(x == null){
                continue;
            }
            if(x.equals(carBookings)){
                return true;
            }
        }
        return false;
    }
}
