package com.hamdi.car;

import com.hamdi.user.User;

import java.math.BigDecimal;
import java.util.UUID;

public class Car {
    private UUID id;
    private String regNumber;
    private BigDecimal rentalPricePerDay;
    private Brand brand;
    public static Car[] cars;
    public static int lastIndex;

    static {
        cars = new Car[10];
        lastIndex = 0;
        cars[lastIndex++] = new Car(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"),"J88H6",new BigDecimal("22.5"),Brand.TESLA,true);
        cars[lastIndex++] = new Car(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"),"EER88R",new BigDecimal("20.45"),Brand.TOYOTA,false);
    }

    public Car() {}

    public Car(String regNumber, BigDecimal rentalPricePerDay, Brand brand, boolean isElectric) {
        id = UUID.randomUUID();
        this.regNumber = regNumber;
        this.rentalPricePerDay = rentalPricePerDay;
        this.brand = brand;
        this.isElectric = isElectric;
    }
    public Car(UUID id,String regNumber, BigDecimal rentalPricePerDay, Brand brand, boolean isElectric) {
        this.id = id;
        this.regNumber = regNumber;
        this.rentalPricePerDay = rentalPricePerDay;
        this.brand = brand;
        this.isElectric = isElectric;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public BigDecimal getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public void setRentalPricePerDay(BigDecimal rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", regNumber='" + regNumber + '\'' +
                ", rentalPricePerDay=" + rentalPricePerDay +
                ", brand=" + brand +
                ", isElectric=" + isElectric +
                '}';
    }

    private boolean isElectric;
}
