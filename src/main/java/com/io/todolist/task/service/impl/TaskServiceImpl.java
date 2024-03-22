package com.io.todolist.task.service.impl;

import com.io.todolist.account.entity.Users;
import com.io.todolist.account.repository.UserRepository;
import com.io.todolist.task.dto.TaskReqDto;
import com.io.todolist.task.dto.TaskResDto;
import com.io.todolist.task.entity.Task;
import com.io.todolist.task.repository.TaskRepository;
import com.io.todolist.task.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(UserRepository userRepository,
                           TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
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

    @Override
    public List<Task> getTasks(Long id) {
        // TODO: Collections.singleton 대신 Repository에서 id별 task 조회하는 메서드 만들것
        return taskRepository.findAllById(Collections.singleton(id));
    }
}
