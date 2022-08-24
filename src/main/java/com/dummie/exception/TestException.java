package com.dummie.exception;

public class TestException extends Exception {

    private String reference;


    public TestException(String message) {
        super(message);
    }


    public TestException(String message, int code, String reference) {
        super(message);
        this.reference = reference;
    }


    public TestException(String message, Throwable cause) {
        super(message, cause);
    }


    public TestException(String message, int code, String reference, Throwable cause) {
        super(message, cause);
        this.reference = reference;
    }


    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
