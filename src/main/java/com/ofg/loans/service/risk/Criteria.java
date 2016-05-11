package com.ofg.loans.service.risk;

import com.ofg.loans.Exceptions.ApplicationDenided;

/**
 * Created by sockor on 10.05.2016.
 */
public interface Criteria {
    void check() throws ApplicationDenided;
}
