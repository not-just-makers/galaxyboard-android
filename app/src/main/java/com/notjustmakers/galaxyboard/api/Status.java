package com.notjustmakers.galaxyboard.api;

/**
 * API status.
 *
 * @author Andres Sanchez (andressanchez)
 */
public class Status {

    public static final Status OK = new Status("OK", false);

    private String message;
    private boolean isError;

    /**
     * Create a status with a message and a boolean representing if there was an error.
     *
     * @param message Status message.
     * @param isError True, if there was an error; false, otherwise.
     */
    public Status(String message, boolean isError) {
        this.message = message;
        this.isError = isError;
    }

    /**
     * Create an error status with a message.
     *
     * @param error Error message.
     */
    public static Status error(String error) {
        return new Status(error, true);
    }

    /**
     * Create a successful status with a message.
     *
     * @param message Status message.
     */
    public static Status ok(String message) {
        return new Status(message, false);
    }

    /**
     * Get the associated message.
     *
     * @return Message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * If it was an error or not.
     *
     * @return True, if there was an error; false, otherwise.
     */
    public boolean isError() {
        return isError;
    }
}