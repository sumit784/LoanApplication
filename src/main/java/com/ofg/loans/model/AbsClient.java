package com.ofg.loans.model;

/**
 * Created by sockor on 10.05.2016.
 */
public class AbsClient {

    private double income;

    private double expense;

    private double capitalRating;

    private Boolean wasDelay;

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public double getCapitalRating() {
        return capitalRating;
    }

    public void setCapitalRating(double capitalRating) {
        this.capitalRating = capitalRating;
    }

    public Boolean getWasDelay() {
        return wasDelay;
    }

    public void setWasDelay(Boolean wasDelay) {
        this.wasDelay = wasDelay;
    }


}
