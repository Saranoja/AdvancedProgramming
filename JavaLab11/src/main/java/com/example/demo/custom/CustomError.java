package com.example.demo.custom;

/**
 * @author : Calin Irina, I2E2
 */

import org.springframework.stereotype.Component;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Component
public class CustomError extends Error {
    public CustomError() {
        super();
    }

    public CustomError(String message) {
        super(message);
    }
}
