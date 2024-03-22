package com.io.todolist.task.dto;

import com.io.todolist.account.dto.AccountResDto;
import com.io.todolist.account.entity.Users;
import com.io.todolist.task.entity.Task;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

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
