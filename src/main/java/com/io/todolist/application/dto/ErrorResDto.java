package com.io.todolist.application.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResDto {
    private String code;

    private String message;

    private int status;
}