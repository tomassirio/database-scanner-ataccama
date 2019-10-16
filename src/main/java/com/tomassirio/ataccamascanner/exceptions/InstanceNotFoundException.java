package com.tomassirio.ataccamascanner.exceptions;

import lombok.Getter;

@Getter
public class InstanceNotFoundException extends Exception {

    private static final String MESSAGE = "Instance was not found: ";

    public InstanceNotFoundException(String message) {
        super(MESSAGE + message);

    }
}