package com.io.todolist;

import com.io.todolist.application.dto.AccountReqDto;
import com.io.todolist.application.dto.AccountResDto;
import com.io.todolist.application.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MateIntegrationTest {

    @Autowired
    private UserService userService;

//
//    @Autowired
//    private MateService mateService;
//
//    @Autowired
//    private MateRepository mateRepository;

//    @Order(1)
//    @Test
//    @DisplayName("Mate 생성 테스트")
//    void Create_Mate_Test() {
//
//        AccountReqDto.SignUp request = AccountReqDto.SignUp.builder()
//                .username("mate_test")
//                .nickname("mate_test")
//                .password("mate_test")
//                .emailAddress("test@test.com")
//                .build();
//
//        AccountResDto.UserInfo response = userService.signup(request);
//
//        MateReqDto.CreateMate createRequest = MateReqDto.CreateMate.builder()
//                .userName(response.getUserName())
//                .mateName("test_mate_name")
//                .build();
//
//        MateResDto.MateInfo mateInfo = mateService.createMate(createRequest);
//
//        assertThat(mateInfo.getMateName()).isNotNull();
//        // createRequest로부터 mateName을 가져와서 mateRepository에서 해당 mate를 찾는다
//        Mate foundMate = mateRepository.findByMateName(createRequest.getMateName());
//
//        // 찾은 Mate 객체의 mateName 속성과 mateInfo 객체의 mateName 속성을 비교하여 일치하는지 확인한다
//        assertThat(foundMate.getMateName()).isEqualTo(mateInfo.getMateName());
//
////        System.out.println(mateInfo.getMateName(), matein);

//    }
}
