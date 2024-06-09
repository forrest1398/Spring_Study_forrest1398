package com.jungle.spring_study_forrest1398.common;


import lombok.Getter;

@Getter
public enum StatusEnum {
    OK(200, "OK"), BAD_REQUEST(400, "DAB_REQUEST"),
    NOT_FOUND(404, "NOT_FOUND"),
    INTERNAL_SERER_ERROR(500, "INTERNAL_SERER_ERROR");
    int statusCode;
    String message;

    StatusEnum(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}