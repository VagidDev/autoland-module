package com.college.model.database.exceptions;

public class CascadeDependencyException extends Exception {
    public CascadeDependencyException(String message) {
        super(message);
    }
}
