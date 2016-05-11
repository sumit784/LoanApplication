package com.ofg.loans.model;

import com.ofg.loans.Exceptions.ApplicationDenided;
import com.ofg.loans.service.risk.RiskService;

import java.net.InetAddress;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Client entity.
 */
public class Client extends AbsClient{

    private String firstName;

    private String lastName;

    private int age;

    private int MAXIMUM_ATTEMPTS = RiskService.MAXIMUM_ATTEMPTS_PER_IP_ADDRESS;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void checkOfIP() throws ApplicationDenided
    {
        LinkedHashMap<Integer, InetAddress> uniqueDays = new LinkedHashMap<>();
        for (Map.Entry<Date, InetAddress> entry : inetAddress.entrySet()) {
            LocalDateTime localDateTime = dateToLocalDateTime(entry.getKey());
            LocalDateTime startOfDay = localDateTime.with(LocalDateTime.MIN);
            uniqueDays.put(startOfDay.getDayOfYear(),entry.getValue());
        }
        if (inetAddress.size()==MAXIMUM_ATTEMPTS && uniqueDays.size()==1) throw new ApplicationDenided();

    }

    private static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
    }

    public void checkEntity() throws ApplicationDenided
    {
        if (getAge()!=0 && getAge()<21) throw new ApplicationDenided();
    }

    public void financialOpportunity() throws ApplicationDenided
    {
        if (getIncome()<getExpense()) throw new ApplicationDenided();
    }

    public void reliabilityCheck() throws ApplicationDenided
    {
        if (getWasDelay()!=null && getWasDelay()) throw new ApplicationDenided();
    }

    public Client() {
        this.inetAddress = new LinkedHashMap<Date, InetAddress>(){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Date, InetAddress> eldest)
            {
                return this.size() > MAXIMUM_ATTEMPTS;
            }
        };
    }

    public void addInetAddress(Date date, InetAddress inetAddress) {
        this.inetAddress.put(date,inetAddress);
    }

    private LinkedHashMap<Date, InetAddress> inetAddress;


}
