package com.hamdi.booking;
import com.hamdi.car.Car;
import com.hamdi.car.CarService;
import com.hamdi.user.User;
import com.hamdi.user.UserService;
import com.hamdi.utility.ArrayHelper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class CarBookingService {
    CarBookingDao carBookingDao = new CarBookingDao();
    ArrayHelper arrayHelper = new ArrayHelper();
    UserService userService = new UserService();
    CarService carService = new CarService();

    public CarBooking lookUp(UUID id) {
        try {
            return carBookingDao.findBookingById(id);
        } catch (Exception e){
            System.out.println("Car booking not found!\n");
            return null;
        }
    }

    public CarBooking bookCar(UUID userId, UUID carId, LocalDate startDate, LocalDate endDate) {
        CarBooking carBooking;

        User user;
        if (userService.lookUp(userId) != null){
            user = userService.lookUp(userId);
        } else {
            return null;
        }
        Car car;
        if(carService.lookUp(carId) != null){
            car = carService.lookUp(carId);
        } else {
            return null;
        }
        if(!validateDate(startDate,endDate)){
            System.out.println("Dates are not valid!\n");
            return null;
        }
        CarBooking[] bookings = carBookingDao.getBookings();
        for (CarBooking booking : bookings) {
            if(booking != null){
                if((booking.getCar().getId().equals(carId)) && booking.getStatus() == BookingStatus.ACTIVE){
                    System.out.println("Car is not available! Planned return date: "+booking.getEndDate()+"\n");
                    return null;
                }
            }
        }
        long days = ChronoUnit.DAYS.between(startDate,endDate);
        BigDecimal price = car.getRentalPricePerDay().multiply(BigDecimal.valueOf(days));
        carBooking = new CarBooking(user,car,startDate,endDate,price,BookingStatus.ACTIVE, LocalDateTime.now());
        carBookingDao.saveBooking(carBooking);
        System.out.println("car booked!\n");
        return carBooking;
    }

    public boolean deleteBooking(UUID id){
        return carBookingDao.deleteBooking(id);
    }

    public CarBooking[] viewUserBookings(UUID userId){
        CarBooking[] result = new CarBooking[1];
        int lastIndex = 0;
        if(viewAllBookings()==null){
            return null;
        }
        for (CarBooking booking : viewAllBookings()) {
            if((booking != null) &&(booking.getUser().getId().equals(userId))){
                if(lastIndex == 0){
                    result[0] = booking;
                    lastIndex++;
                } else{
                    result =  arrayHelper.add(result,booking,lastIndex++);
                }
            }
        }
        return result;
    }

    public CarBooking[] viewAllBookings(){
        CarBooking[] result = new CarBooking[1];
        int lastIndex = 0;
        for (CarBooking booking : carBookingDao.getBookings()) {
                if(booking != null){
                    if(lastIndex == 0){
                        result[0] = booking;
                        lastIndex++;
                    } else{
                        result = arrayHelper.add(result,booking,lastIndex++);
                    }
                }
            }
        return result;
    }

    public Car[] viewAvailableCars(){
        Car[] result = new Car[1];
        int lastIndex = 0;
        Car[] cars = carService.getAll();
        CarBooking[] carBookings = carBookingDao.getBookings();
        for (Car car : cars) {
            boolean match = false;
            if (car != null) {
                for (CarBooking carBooking : carBookings) {
                    if ((carBooking != null) && carBooking.getStatus() == BookingStatus.ACTIVE) {
                        match = car.equals(carBooking.getCar());
                        if (match) {
                            break;
                        }
                    }
                }
                if (!match) {
                    result = arrayHelper.add(result,car,lastIndex++);
                }
            }
        }
        return result;
    }

    private boolean validateDate(LocalDate start, LocalDate end){
        return !start.isBefore(LocalDate.now()) && start.isBefore(end);
    }
}
