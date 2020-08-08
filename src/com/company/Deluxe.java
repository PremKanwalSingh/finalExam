package com.company;

public class Deluxe extends Savings implements Compound_Interest {

    double interestRate;
    public double compoundInterest;

    Deluxe(String customerNumber, String customerName, String typeOfSavings, double initialDeposit, int numberOfYears) {
        super(customerNumber, customerName, typeOfSavings, initialDeposit, numberOfYears);
        this.interestRate = 15;
    }

    @Override
    public double generateTable() {
        double interestAmt = initialDeposit * this.interestRate/100;

        return interestAmt;
    }
}
