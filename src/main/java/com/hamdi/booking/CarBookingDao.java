package com.hamdi.booking;


import com.hamdi.user.User;
import com.hamdi.utility.ArrayHelper;

import java.util.Arrays;
import java.util.UUID;

public class CarBookingDao {
    private static CarBooking[] carBookings = new CarBooking[1];
    private static int lastIndex;
    private ArrayHelper arrayHelper = new ArrayHelper();
    public boolean saveBooking(CarBooking booking){
        if(!carBookingsExists(booking)){//if carBookings doesn't exist, add to array and increment CarBooking.lastIndex
            carBookings = arrayHelper.add(carBookings,booking,lastIndex++);
            return true;
        }
        return false;
    }

    public boolean deleteBooking(UUID id){
        boolean result = false;
        int length = carBookings.length;
        CarBooking[] temp = new CarBooking[length];
        int nextIndex = 0;
        for (int i = 0; i < carBookings.length; i++) {
                if(carBookings[i] != null){
                    if(!carBookings[i].getId().equals(id)){
                        temp[nextIndex++] = carBookings[i];
                    } else {
                        System.out.println("deleted: "+carBookings[i].getId());
                        result = true;
                    }
                }
        }
        for (int i = 0; i < temp.length; i++) {
            if(temp[i] != null){
                lastIndex = i+1;
            }
        }
        carBookings = temp;
        return result;
    }

    public CarBooking findBookingById(UUID id){
        for (CarBooking booking : getBookings()) {
            if(booking.getId().equals(id)){
                return booking;
            }
        }
        return null;
    }
    public CarBooking[] getBookings(){
        CarBooking[] result = new CarBooking[lastIndex];
        for (int i = 0; i < lastIndex; i++) {
            result[i] = carBookings[i];
        }
        return result;
    }
    private boolean carBookingsExists(CarBooking carBooking){
        for (CarBooking booking : getBookings()) {
            if((booking !=null) && (booking.equals(carBooking))){
                return true;
            }
        }
        return false;
    }
}
