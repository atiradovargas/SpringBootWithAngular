package com.dummie.beans;

public class ErrorAlert {

    private String message;

    public ErrorAlert(){

    }

    public ErrorAlert(String message){
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
