package com.company;

public class Savings {
    public String customerNumber,customerName, typeOfSavings;
    public double initialDeposit;
    public int numberOfYears;

    Savings(String customerNumber, String customerName, String typeOfSavings, double initialDeposit, int numberOfYears) {
        this.customerName = customerName;
        this.customerNumber = customerNumber;
        this.typeOfSavings = typeOfSavings;
        this.initialDeposit = initialDeposit;
        this.numberOfYears = numberOfYears;
    }

    Savings() {}

}
