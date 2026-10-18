package com.hamdi;

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
        Scanner scanner = new Scanner(System.in);
        CarBookingService carBookingService = new CarBookingService();
        CarService carService = new CarService();
        UserService userService = new UserService();
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
                    } catch (NullPointerException e){
                        System.out.println("***No bookings in system***");
                        System.out.println(Arrays.toString(carBookingService.viewAllBookings()));
                        break;
                    }
                case 3:
                    System.out.println("Please enter UserID: ");
                    try {
                        CarBooking[] userBookings = carBookingService.viewUserBookings(UUID.fromString(scanner.next()));
                        System.out.println("Bookings for: "+userBookings[0].getUser().getName());
                        System.out.println(Arrays.toString(userBookings)+"\n");
                        break;
                    } catch (NullPointerException e){
                        System.out.println("***No bookings found***\n");
//                        e.printStackTrace();
                        break;
                    } catch (IllegalArgumentException e){
                        System.out.println("***Invalid UserID***\n");
                        break;
                    }
                case 4:
                    try {
                        CarBooking[] carBooking = carBookingService.viewAllBookings();
                        System.out.println("Bookings: \n");
                        if(carBooking[0] != null){
                            for (int i = 0; i < carBooking.length; i++) {
                                if(carBooking[i] != null) {
                                    System.out.println("#"+i+": "+carBooking[i]+"\n");
                                }
                            }
                        }
                        else {
                            throw new NullPointerException();
                        };
                        break;
                    } catch (NullPointerException e){
                        System.out.println("***Could not find any bookings in the system***\n");
                        break;
                    }
                case 5:
                    try {
                        System.out.println("Available cars: "+Arrays.toString(carBookingService.viewAvailableCars())+"\n");
                        break;
                    } catch (NullPointerException e){
                        System.out.println("***Could not find any available cars in the system***\n");
                        break;
                    }
                case 6:
                    try {
                        System.out.println(Arrays.toString(carService.getElectric())+"\n");
                        break;
                    } catch (NullPointerException e){
                        System.out.println("***Could not find any electric cars in system***\n");
                        break;
                    }
                case 7:
                    try {
                        System.out.println(Arrays.toString(userService.getAll()));
                        break;
                    } catch (NullPointerException e){
                        System.out.println("***There are currently no users in the system***\n");
                        break;
                    }
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

