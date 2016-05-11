package com.ofg.loans.Exceptions;

/**
 * Created by sockor on 10.05.2016.
 */
public class ApplicationDenided extends Exception {
    public ApplicationDenided() { super(); }
    public ApplicationDenided(String message) { super(message); }
    public ApplicationDenided(String message, Throwable cause) { super(message, cause); }
    public ApplicationDenided(Throwable cause) { super(cause); }
}
