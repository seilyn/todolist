package com.io.todolist.task.controller;

import com.io.todolist.common.dto.CommonResponse;
import com.io.todolist.task.dto.TaskReqDto;
import com.io.todolist.task.dto.TaskResDto;
import com.io.todolist.task.entity.Task;
import com.io.todolist.task.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/app")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * 태스크 등록
     * @param id
     * @param request
     * @return
     */
    @PostMapping("/tasks/{id}")
    public ResponseEntity<CommonResponse> addTasks (@PathVariable Long id, @RequestBody TaskReqDto.AddTasks request) {
        TaskResDto.TaskInfo taskInfo = taskService.addTasks(id, request);

        CommonResponse response = CommonResponse.builder()
                .success(true)
                .response(taskInfo)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<CommonResponse> getTasks(@PathVariable Long id) {
        List<Task> taskList = taskService.getTasks(id);

        CommonResponse response = CommonResponse.builder()
                .success(true)
                .response(taskList)
                .build();

        return ResponseEntity.ok(response);
    }
}
