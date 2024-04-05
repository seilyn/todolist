package com.io.todolist.application.service.impl;

import com.io.todolist.domain.Users;
import com.io.todolist.infrasturcture.persistence.UserRepository;
import com.io.todolist.application.dto.MateReqDto;
import com.io.todolist.application.dto.MateResDto;
import com.io.todolist.domain.Mate;
import com.io.todolist.infrasturcture.persistence.MateRepository;
import com.io.todolist.application.service.MateService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class MateServiceImpl implements MateService {

    private UserRepository userRepository;

    private MateRepository mateRepository;

    public MateServiceImpl(UserRepository userRepository, MateRepository mateRepository) {
        this.userRepository = userRepository;
        this.mateRepository = mateRepository;
    }

    @Override
    public MateResDto.MateInfo createMate(MateReqDto.CreateMate request) {
        // 만드는 유저의 이름을 가져옴.
        Users leader = userRepository.findByUserName(request.getUserName());

        // Set에 추가 함.
        Set<Users> usersSet = new HashSet<>();
        usersSet.add(leader);


        Mate mate = Mate.builder()
                .inviteKey(UUID.randomUUID().toString())
                .mateName(request.getMateName())
                .leaderName(request.getUserName())
                .users(usersSet)
                .build();

        return MateResDto.MateInfo.of(mateRepository.save(mate));
    }
}
