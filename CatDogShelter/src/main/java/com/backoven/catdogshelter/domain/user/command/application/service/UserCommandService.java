//package com.backoven.catdogshelter.domain.user.command.application.service;
//
//import com.backoven.catdogshelter.domain.user.command.domain.aggregate.entity.UserEntity;
//import com.backoven.catdogshelter.domain.user.command.domain.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class UserCommandService {
//    private final UserRepository userRepository;
//
//    public UserEntity findById(Long id) {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
//    }
//}
