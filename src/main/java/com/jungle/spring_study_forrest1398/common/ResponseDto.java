package com.jungle.spring_study_forrest1398.common;

import lombok.Getter;

@Getter
public class ResponseDto {
    private StatusEnum status;
    private String message;
    private Object data;

    public ResponseDto() {
        this.status = StatusEnum.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }

    public ResponseDto(StatusEnum status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}