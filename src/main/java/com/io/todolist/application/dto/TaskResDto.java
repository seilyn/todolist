package com.io.todolist.application.dto;

import com.io.todolist.domain.Task;
import lombok.Builder;
import lombok.Data;

public class TaskResDto {
    @Builder
    @Data
    public static class TaskInfo {
        private String contents;

        private String author;

        private String deadline;

        public static TaskResDto.TaskInfo of(Task task) {
            if (task == null) {
                return null;
            }

            return TaskInfo.builder()
                    .author(task.getAuthor())
                    .contents(task.getContents())
                    .deadline(task.getDeadline())
                    .build();
        }
    }
}
