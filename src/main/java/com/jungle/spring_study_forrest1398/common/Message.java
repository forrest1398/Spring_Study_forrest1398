package com.jungle.spring_study_forrest1398.common;

import lombok.Data;

@Data
public class Message {
    private String message;
    private Object data;
    private StatusEnum status;

    public Message() {
        this.status = StatusEnum.BAD_REQUEST;
        this.message = null;
        this.data = null;
    }
}
