package com.jungle.spring_study_forrest1398.exception;

import lombok.Getter;

import javax.lang.model.type.ErrorType;

@Getter
public class ErrorResponse {

    private ErrorType errorType;
    private String message;
    private String path;

    public ErrorResponse(ErrorType errorType, String message, String path) {
        this.errorType = errorType;
        this.message = message;
        this.path = path;
    }

    public ErrorResponse(ErrorType errorType, String message) {
        this.errorType = errorType;
        this.message = message;
    }

}