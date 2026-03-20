package ru.vladislav.JavaNaumen.exception;

public record ApiError(String message) {

    public static ApiError of(Exception e) {
        return new ApiError(e.getMessage());
    }
}
