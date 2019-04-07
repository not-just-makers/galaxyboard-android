package com.notjustmakers.galaxyboard.model;

public class Status {

    private String message;
    private boolean isError;

    public Status(String message, boolean isError) {
        this.message = message;
        this.isError = isError;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return isError;
    }
}
