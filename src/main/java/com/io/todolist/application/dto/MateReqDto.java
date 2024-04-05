package com.io.todolist.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class MateReqDto {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMate {
        @NotNull
        private String userName;

        @NotNull
        private String mateName;
    }
}
