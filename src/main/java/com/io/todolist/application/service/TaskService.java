package com.io.todolist.application.service;

import com.io.todolist.domain.Mate;
import com.io.todolist.domain.MateUser;
import com.io.todolist.domain.Users;
import com.io.todolist.infrasturcture.persistence.MateUserRepository;
import com.io.todolist.infrasturcture.persistence.UserRepository;
import com.io.todolist.application.dto.TaskReqDto;
import com.io.todolist.application.dto.TaskResDto;
import com.io.todolist.domain.Task;
import com.io.todolist.infrasturcture.persistence.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final MateUserRepository mateUserRepository;

    public TaskService(UserRepository userRepository,
                       TaskRepository taskRepository, MateUserRepository mateUserRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.mateUserRepository = mateUserRepository;
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

    public List<Task> getTasksForUser(Long userId) {
        // 사용자 조회
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 사용자가 속한 그룹 조회
        List<MateUser> mateUsers = mateUserRepository.findByUser(user);

        // 사용자와 그룹에 속한 다른 사용자의 Task 조회
        List<Task> tasks = new ArrayList<>();
        for (MateUser mateUser : mateUsers) {
            Mate mate = mateUser.getMate();
            List<MateUser> otherMateUsers = mateUserRepository.findByMateAndUserNot(mate, user);
            for (MateUser otherMateUser : otherMateUsers) {
                Users otherUser = otherMateUser.getUser();
                List<Task> otherUserTasks = taskRepository.findTaskByUser(otherUser);
                tasks.addAll(otherUserTasks);
            }
        }

        // 사용자의 자신의 Task도 추가
        List<Task> userTasks = taskRepository.findTaskByUser(user);
        tasks.addAll(userTasks);

        return tasks;
    }
}
