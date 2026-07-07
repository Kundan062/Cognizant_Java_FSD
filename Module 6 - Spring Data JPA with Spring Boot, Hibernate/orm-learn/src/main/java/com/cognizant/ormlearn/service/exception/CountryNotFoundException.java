package com.cognizant.ormlearn.service.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(String code) {
        super("Country not found with code: " + code);
    }
}
