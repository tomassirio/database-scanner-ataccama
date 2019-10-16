package com.tomassirio.ataccamascanner.exceptions;

import lombok.Getter;

@Getter
public class TestConnectionException extends Exception {

    private static final String MESSAGE = "There was a failure on the Connection: ";

    public TestConnectionException(String provider, String host, String port, String user, String password) {
        super(MESSAGE.concat("Provider: ").concat(provider).concat(", ")
                .concat("Host: ").concat(host).concat(", ")
                .concat("Port: ").concat(port).concat(", ")
                .concat("User: ").concat(user).concat(", ")
                .concat("Password: ").concat(password).concat(", "));
    }
}