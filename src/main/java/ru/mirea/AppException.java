package ru.mirea;

public class AppException {
    private String error;

    public AppException(String message) {
        error = message;
    }

    public String getError() {
        return error;
    }
}