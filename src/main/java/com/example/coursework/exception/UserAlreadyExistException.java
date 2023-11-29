package com.example.coursework.exception;

public class UserAlreadyExistException extends Exception{

    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
