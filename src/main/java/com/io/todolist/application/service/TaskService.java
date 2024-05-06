package com.io.todolist.application.service;

import com.io.todolist.domain.*;
import com.io.todolist.infrasturcture.persistence.MateUserRepository;
import com.io.todolist.infrasturcture.persistence.UserRepository;
import com.io.todolist.application.dto.TaskReqDto;
import com.io.todolist.application.dto.TaskResDto;
import com.io.todolist.infrasturcture.persistence.TaskRepository;
import com.io.todolist.infrasturcture.persistence.UserTaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final MateUserRepository mateUserRepository;

    private final UserTaskRepository userTaskRepository;
    public TaskService(UserRepository userRepository,
                       TaskRepository taskRepository, MateUserRepository mateUserRepository, UserTaskRepository userTaskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.mateUserRepository = mateUserRepository;
        this.userTaskRepository = userTaskRepository;
    }

    @Transactional
    public TaskResDto.TaskInfo addTasks(Long id, TaskReqDto.AddTasks request) throws RuntimeException{

        Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException());

        Task task = Task.builder()

                .author(user.getUserName())
                .contents(request.getContents())
                .deadline(request.getDeadline())
                .isCompleted(false)
                .build();

        UserTask userTask = new UserTask();
        userTask.setTask(task);
        userTask.setUser(user);
        userTaskRepository.save(userTask);

        return TaskResDto.TaskInfo.of(taskRepository.save(task));
    }

    public List<Task> getTasks(Long id) {
        // TODO: Collections.singleton 대신 Repository에서 id별 task 조회하는 메서드 만들것
        return taskRepository.findAllById(Collections.singleton(id));
    }

    public List<Task> getTasksForUser(Long userId) {
        // 사용자 조회
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 사용자의 작업을 저장할 리스트
        List<Task> tasks = new ArrayList<>();

        // 사용자가 속한 그룹 조회
        List<MateUser> mateUsers = mateUserRepository.findByUser(user);



        return tasks;
    }

}
