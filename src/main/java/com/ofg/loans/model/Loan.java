package com.ofg.loans.model;

import com.ofg.loans.Exceptions.ApplicationDenided;
import com.ofg.loans.service.risk.RiskService;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by sockor on 10.05.2016.
 */
public class Loan {

    private double amount;
    private int term;
    private Date startDate;
    private LoanTypes type;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public LoanTypes getType() {
        return type;
    }

    public void setType(LoanTypes type) {
        this.type = type;
    }

    public void checkOfAmount() throws ApplicationDenided
    {
        if (getAmount()!=0 && getAmount()>RiskService.MAXIMUM_LOAN_AMOUNT) throw new ApplicationDenided();
    }

    public void checkOfTime() throws ApplicationDenided
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        Date min = cal.getTime();

        cal.set(Calendar.HOUR_OF_DAY,6);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        Date max = cal.getTime();

        if (startDate!=null && startDate.after(min) && startDate.before(max))  throw new ApplicationDenided();
    }

}
