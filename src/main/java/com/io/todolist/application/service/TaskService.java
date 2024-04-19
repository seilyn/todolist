package com.io.todolist.application.service;

import com.io.todolist.domain.Users;
import com.io.todolist.infrasturcture.persistence.UserRepository;
import com.io.todolist.application.dto.TaskReqDto;
import com.io.todolist.application.dto.TaskResDto;
import com.io.todolist.domain.Task;
import com.io.todolist.infrasturcture.persistence.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public TaskService(UserRepository userRepository,
                       TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public TaskResDto.TaskInfo addTasks(Long id, TaskReqDto.AddTasks request) throws RuntimeException{

        Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException());

        Task task = Task.builder()

                .author(user.getUserName())
                .contents(request.getContents())
                .deadline(request.getDeadline())
                .isCompleted(false)
                .build();

        return TaskResDto.TaskInfo.of(taskRepository.save(task));
    }

    public List<Task> getTasks(Long id) {
        // TODO: Collections.singleton 대신 Repository에서 id별 task 조회하는 메서드 만들것
        return taskRepository.findAllById(Collections.singleton(id));
    }
}
