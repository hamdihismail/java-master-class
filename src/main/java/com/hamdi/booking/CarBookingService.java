package com.hamdi.booking;
import com.hamdi.car.Car;
import com.hamdi.car.CarDao;
import com.hamdi.car.CarService;
import com.hamdi.user.User;
import com.hamdi.user.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.UUID;

public class CarBookingService {
    CarBookingDao carBookingDao = new CarBookingDao();

    public CarBooking lookUp(UUID id) {
        try {
            return carBookingDao.getCarBooking(id);
        } catch (Exception e){
            System.out.println("Car booking not found!\n");
            return null;
        }
    }

    public CarBooking bookCar(UUID userId, UUID carId, LocalDate startDate, LocalDate endDate) {
        CarBooking carBooking;
// 1. Look up user by userId
// - If not found → throw exception or print error
        UserService userService = new UserService();
        User user;
        if (userService.lookUp(userId) != null){
            System.out.println("user valid\n");
            user = userService.lookUp(userId);
        } else {
            return null;
        }
// 2. Look up car by carId
// - If not found → throw exception or print error
        CarService carService = new CarService();
        Car car;
        if(carService.lookUp(carId) != null){
            System.out.println("car valid\n");
            car = carService.lookUp(carId);
        } else {
            return null;
        }
// 3. Validate dates:
// - startDate must not be in the past
// - endDate must be after startDate
// - If invalid → throw IllegalArgumentException or print error
        if(!validateDate(startDate,endDate)){
            System.out.println("Dates are not valid!\n");
            return null;
        }
        System.out.println("dates valid\n");
// 4. Get all current bookings
        CarBooking[] bookings = carBookingDao.getAllCarBookings();
// 5. Check if any active booking already has this car
// - If yes → throw exception or print error (car is not available)
        for (CarBooking booking : bookings) {
            if(booking != null){
                if((booking.getCar().getId().equals(carId)) && (startDate.isBefore(booking.getEndDate())) && booking.getStatus() == BookingStatus.ACTIVE){
                    System.out.println("Car is not available! "+booking.getEndDate()+"\n");
                    return null;
                }
            }
        }
        System.out.println("car is available\n");
// 6. Calculate number of days between startDate and endDate using ChronoUnit.DAYS.between(startDate, endDate)
        long days = ChronoUnit.DAYS.between(startDate,endDate);
// 7. Calculate price: car.getRentalPricePerDay() × numberOfDays
        BigDecimal price = car.getRentalPricePerDay().multiply(BigDecimal.valueOf(days));
// 8. Create a new CarBooking with: UUID, user, car, startDate, endDate, price, BookingStatus.ACTIVE, and bookedAt = LocalDateTime.now()
//        UUID bookingId = UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3");
        carBooking = new CarBooking(user,car,startDate,endDate,price,BookingStatus.ACTIVE, LocalDateTime.now());
        System.out.println("booking created!\n");
// 9. Save the booking via the DAO
        carBookingDao.addCarBooking(carBooking);
// 10. Return the saved CarBooking
        System.out.println("car booked!\n");
//        System.out.println("ID: "+carBooking.getId());
        return carBooking;
    }

    public boolean deleteBooking(UUID id){
        CarBookingDao carBookingDao = new CarBookingDao();
        return carBookingDao.deleteCarBooking(id);
    }

    public boolean viewUserBookings(UUID userId){
        CarBookingDao carBookingDao = new CarBookingDao();
        boolean result = false;
        for (CarBooking booking : carBookingDao.getAllCarBookings()) {
            if((booking != null) && (booking.getId().equals(userId))){
                System.out.println("\n "+booking);
                result = true;
            }
        }
        return result;
    }

    public boolean viewAllBookings(){
        if(carBookingDao.getAllCarBookings() !=null){
            for (CarBooking booking : carBookingDao.getAllCarBookings()) {
                if(booking != null){
                    System.out.println(booking);
                }
            }
            return true;
        }
        return false;
    }

    public boolean viewAvailableCars(){
        boolean result = false;
        CarService carService = new CarService();
        Car[] cars = carService.getAll();
        CarBooking[] carBookings = carBookingDao.getAllCarBookings();
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
                    System.out.println("Is available: " + car + "\n");
                    result = true;
                }
            }
        }
        return result;
    }

    private boolean validateDate(LocalDate start, LocalDate end){
        return start.isAfter(LocalDate.now()) && start.isBefore(end);
    }
}
