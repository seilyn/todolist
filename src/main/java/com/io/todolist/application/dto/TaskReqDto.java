package com.io.todolist.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class TaskReqDto {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddTasks {
        private Long id;

        private String contents;

        private String author;

        private String deadline;

        private String isCompleted;

    }
}
