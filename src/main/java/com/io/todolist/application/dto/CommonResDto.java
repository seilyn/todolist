package com.io.todolist.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
@Builder
public class CommonResDto {

    @Builder.Default
    private String uid = UUID.randomUUID().toString();

    @Builder.Default
    private Date dateTime = new Date();

    private Boolean success;

    private Object response;

    private Object error;

}
