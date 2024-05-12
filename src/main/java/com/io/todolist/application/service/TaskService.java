package com.io.todolist.application.service;

import com.io.todolist.domain.*;
import com.io.todolist.infrasturcture.persistence.*;
import com.io.todolist.application.dto.TaskReqDto;
import com.io.todolist.application.dto.TaskResDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final MateUserRepository mateUserRepository;

    private final MateRepository mateRepository;

    private final UserTaskRepository userTaskRepository;

    public TaskService(UserRepository userRepository,
                       TaskRepository taskRepository, MateUserRepository mateUserRepository, MateRepository mateRepository, UserTaskRepository userTaskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.mateUserRepository = mateUserRepository;
        this.mateRepository = mateRepository;
        this.userTaskRepository = userTaskRepository;
    }

    @Transactional
    public TaskResDto.TaskInfo addTasks(Long id, TaskReqDto.AddTasks request) throws RuntimeException {

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

    @Transactional

    public List<Task> getTasksForUser(Long userId) {
        // 1. 사용자 ID로 속한 Mate 가져오기
        List<MateUser> mateUserList = mateUserRepository.findMateByUserId(userId);

        // 사용자의 모든 작업을 저장할 리스트
        Set<Long> taskIdList = new HashSet<>();

        for (MateUser mateUser : mateUserList) {
            // 2. 각 Mate에 속한 모든 사용자의 ID 가져오기
            List<Long> mateUserIdSet = mateUserRepository.findUserIdsByMateId(mateUser.getMate().getId());

            for (Long mateUserId : mateUserIdSet) {
                // 3. 각 사용자 ID로 해당 사용자의 모든 작업 가져오기
                List<Long> idList = userTaskRepository.findUserTasksByUserId(mateUserId);
                taskIdList.addAll(idList);
            }
        }

        List<Task> taskList = taskRepository.findByIdIn(taskIdList);

        return taskList;
    }


}
