package com.io.todolist.task.service;

import com.io.todolist.task.dto.TaskReqDto;
import com.io.todolist.task.dto.TaskResDto;
import com.io.todolist.task.entity.Task;

import java.util.List;

public interface TaskService {
    TaskResDto.TaskInfo addTasks(Long id, TaskReqDto.AddTasks request);

    List<Task> getTasks(Long id);
}
