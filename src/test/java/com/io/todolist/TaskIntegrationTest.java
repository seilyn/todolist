package com.io.todolist;

import com.io.todolist.application.dto.*;
import com.io.todolist.application.service.MateService;
import com.io.todolist.application.service.TaskService;
import com.io.todolist.application.service.UserService;
import com.io.todolist.domain.Mate;
import com.io.todolist.domain.Task;
import com.io.todolist.domain.Users;
import com.io.todolist.infrasturcture.persistence.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskIntegrationTest {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private MateService mateService;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(3)
    void getTask_From_UserId(){

         /*
        Given
         */
        AccountReqDto.SignUp request1 = AccountReqDto.SignUp.builder()
                .username("테스트1")
                .nickname("test1")
                .password("test1")
                .emailAddress("test@test.com")
                .build();

        AccountReqDto.SignUp request2 = AccountReqDto.SignUp.builder()
                .username("테스트2")
                .nickname("test2")
                .password("test2")
                .emailAddress("test2@test.com")
                .build();

        AccountReqDto.SignUp request3 = AccountReqDto.SignUp.builder()
                .username("테스트3")
                .nickname("test3")
                .password("test3")
                .emailAddress("test3@test.com")
                .build();
        /*
        when
         */
        userService.signup(request1);
        userService.signup(request2);
        userService.signup(request3);

        MateReqDto.CreateMate req = MateReqDto.CreateMate.builder()
                .userName("테스트1")
                .userId("1")
                .mateName("테스트그룹1")
                .build();
        MateResDto.MateInfo mate =  mateService.createMate(1L, req);

        mateService.joinMate(mate.getInviteKey(), 2L);

        MateReqDto.CreateMate req2 = MateReqDto.CreateMate.builder()
                .userName("테스트2")
                .userId("2")
                .mateName("테스트그룹2")
                .build();

        TaskReqDto.AddTasks reqTask1 = TaskReqDto.AddTasks.builder()
                .author("테스트1")
                .contents("테스트1의 task")
                .isCompleted("N")
                .deadline("2024-06-01")
                .build();

        TaskReqDto.AddTasks reqTask2 = TaskReqDto.AddTasks.builder()
                .author("테스트2")
                .contents("테스트2의 task")
                .isCompleted("N")
                .deadline("2024-06-03")
                .build();


        taskService.addTasks(1L, reqTask1);
        taskService.addTasks(2L, reqTask2);

//        List<Task> taskList = taskService.getTasksForUser(1L);
//
//        for (int i = 0; i < taskList.size(); i++) {
//            System.out.println(taskList.get(i));
//        }






//        System.out.println(mate.getInviteKey());
    }
}
