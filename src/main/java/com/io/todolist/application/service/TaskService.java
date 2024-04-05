package com.io.todolist.application.service;

import com.io.todolist.application.dto.TaskReqDto;
import com.io.todolist.application.dto.TaskResDto;
import com.io.todolist.domain.Task;

import java.util.List;

public interface TaskService {
    TaskResDto.TaskInfo addTasks(Long id, TaskReqDto.AddTasks request);

    List<Task> getTasks(Long id);
}
