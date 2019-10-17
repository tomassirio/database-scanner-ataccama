package com.tomassirio.ataccamascanner.exceptions;

import lombok.Getter;

@Getter
public class ExistingInstanceException extends Exception {

    private static final String MESSAGE = "An instance with the same attributes does already exist ";

    public ExistingInstanceException(String message) {
        super(MESSAGE + message);

    }
}