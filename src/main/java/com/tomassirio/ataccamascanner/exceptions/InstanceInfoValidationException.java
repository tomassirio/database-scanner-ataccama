package com.tomassirio.ataccamascanner.exceptions;

import lombok.Getter;

@Getter
public class InstanceInfoValidationException extends Exception {

    public InstanceInfoValidationException(String message) {
        super(message);

    }
}