package com.hamdi;
// TODO 1. create a new branch called initial-implementation
// TODO 2. create a package with your name. i.e com.franco and move this file inside the new package
// TODO 3. implement https://amigoscode.com/learn/java-cli-build/lectures/3a83ecf3-e837-4ae5-85a8-f8ae3f60f7f5

import com.hamdi.booking.CarBooking;
import com.hamdi.booking.CarBookingService;
import com.hamdi.car.Car;
import com.hamdi.car.CarService;
import com.hamdi.user.User;
import com.hamdi.user.UserService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args){
        System.out.println(Arrays.toString(Car.cars));
        System.out.println(Arrays.toString(CarBooking.carBookings));
        Scanner scanner = new Scanner(System.in);
        CarBookingService carBookingService = new CarBookingService();
        String options = """
                1. Book a Car
                2. Delete Booking
                3. View User Bookings
                4. View All Bookings
                5. View Available Cars
                6. View Electric Cars
                7. View All Users
                8. Exit
                """;
        String message = options;
        System.out.println(message);
        while(true){
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e){
                System.out.println("***Please enter a valid option***\n");
                scanner.next();
            }
            switch (choice){
                case 1:
                    UserService userService = new UserService();
                    System.out.println("Please enter the UserId: ");
                    UUID userId;
                    try {
                        userId = UUID.fromString(scanner.next());
                    }catch (IllegalArgumentException e){
                        System.out.println("***Invalid UserID***\n");
                        break;
                    }

                    System.out.println("Please enter the CarId: ");
                    UUID carId;
                    try {
                        carId = UUID.fromString(scanner.next());
                    } catch (IllegalArgumentException e){
                        System.out.println("***Invalid CarID***\n");
                        break;
                    }

                    System.out.println("Please enter the start date (year, month, day): ");
                    System.out.println("Enter year: ");
                    int startYear;
                    try {
                        startYear = scanner.nextInt();
                    }catch (InputMismatchException e){
                        System.out.println("*** Invalid year entered! ***");
                        scanner.next();
                        break;
                    }
                    System.out.println("Enter month: ");
                    int startMonth;
                    try {
                        startMonth = scanner.nextInt();
                    }catch (InputMismatchException e){
                        System.out.println("*** Invalid month entered! ***");
                        scanner.next();
                        break;
                    }
                    System.out.println("Enter Day: ");
                    int startDay;
                    try {
                        startDay = scanner.nextInt();
                    }catch (InputMismatchException e){
                        System.out.println("*** Invalid day entered! ***");
                        scanner.next();
                        break;
                    }

                    System.out.println("Please enter the end date (year, month, day): ");
                    System.out.println("Enter year: ");
                    int endYear;
                    try {
                        endYear = scanner.nextInt();
                    }catch (InputMismatchException e){
                        System.out.println("*** Invalid year entered! ***");
                        scanner.next();
                        break;
                    }
                    System.out.println("Enter month: ");
                    int endMonth;
                    try {
                        endMonth = scanner.nextInt();
                    }catch (InputMismatchException e){
                        System.out.println("*** Invalid month entered! ***");
                        scanner.next();
                        break;
                    }
                    System.out.println("Enter Day: ");
                    int endDay;
                    try {
                        endDay = scanner.nextInt();
                    }catch (InputMismatchException e){
                        System.out.println("*** Invalid day entered! ***");
                        scanner.next();
                        break;
                    }

                    LocalDate startDate;
                    try {
                        startDate = LocalDate.of(startYear,startMonth,startDay);
                    }catch (DateTimeException e){
                        System.out.println("*** "+e.getMessage()+" ***\n");
                        break;
                    }
                    LocalDate endDate;
                    try {
                        endDate = LocalDate.of(endYear,endMonth,endDay);
                    } catch (DateTimeException e){
                        System.out.println("*** "+e.getMessage()+" ***\n");
                        break;
                    }
                    try {
                        CarBooking booking = carBookingService.bookCar(userId, carId, startDate,endDate);
                        System.out.println(booking);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                        System.out.println("Booking unsuccessful!");
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Please enter booking id: \n");
                    try {
                        boolean result = carBookingService.deleteBooking(UUID.fromString(scanner.next()));
                        if(!result){
                            System.out.println("***Could not delete, please verify BookingID***\n");
                            break;
                        }
                        System.out.println("Booking deleted\n");
                        break;
                    } catch (IllegalArgumentException e){
                        System.out.println("***Invalid BookingID***\n");
                        break;
                    }
                case 3:
                    System.out.println("Please enter UserID: ");
                    try {
                        boolean result = carBookingService.viewUserBookings(UUID.fromString(scanner.next()));
                        if(!result){
                            System.out.println("***Could not find bookings, please verify UserID***\n");
                            break;
                        }
                        break;
                    } catch (IllegalArgumentException e){
                        System.out.println("***Invalid UserID***\n");
                        break;
                    }
                case 4:
                    boolean result = carBookingService.viewAllBookings();
                    if(!result){
                        System.out.println("***Could not find any bookings in the system***\n");
                    }
                    break;
                case 5:
                    result = carBookingService.viewAvailableCars();
                    if(!result){
                        System.out.println("***Could not find any available cars in the system***\n");
                    }
                    break;
                case 6:
                    result = false;
                    for (Car car : new CarService().getAll()) {
                        if((car != null)&&(car.isElectric())){
                            System.out.println(car+"\n");
                            result = true;
                        }
                    }
                    if(!result){
                        System.out.println("***Could not find any electric cars in system***\n");
                    }
                    break;
                case 7:
                    result = false;
                    for (User user : new UserService().getAll()) {
                        if(user != null){
                            System.out.println(user);
                            result = true;
                        }
                    }
                    if(!result){
                        System.out.println("***There are currently no users in the system***\n");
                    }
                    break;
                case 8:
                   return;
                default:
                    message = options;
                    break;
            }
            System.out.println(message);
        }
    }

}

