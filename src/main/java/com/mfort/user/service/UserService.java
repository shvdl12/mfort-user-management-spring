package com.mfort.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfort.user.exception.DuplicateUserIdException;
import com.mfort.user.model.domain.ParentUser;
import com.mfort.user.model.domain.SitterUser;
import com.mfort.user.model.domain.User;
import com.mfort.user.model.request.RegisterParentRequest;
import com.mfort.user.model.request.RegisterSitterRequest;
import com.mfort.user.model.request.ParentSignUpRequest;
import com.mfort.user.model.request.SitterSignUpRequest;
import com.mfort.user.model.response.UserInfoResponse;
import com.mfort.user.repository.ParentRepository;
import com.mfort.user.repository.SitterRepository;
import com.mfort.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ParentRepository parentRepository;
    private final SitterRepository sitterRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private ObjectMapper objectMapper = new ObjectMapper();


    public SitterUser signUpSitter(SitterSignUpRequest request) {

        if (userRepository.existsByUserId(request.getUserId())) {
            throw new DuplicateUserIdException("이미 존재하는 아이디입니다. (" + request.getUserId() + ")");
        }

        SitterUser sitterUser = SitterUser.builder()
                .name(request.getName())
                .birthAt(request.getBirthAt())
                .gender(request.getGender())
                .userId(request.getUserId())
                .password(passwordEncoder.encode(request.getPassword()))
                .minChildAge(request.getMinChildAge())
                .maxChildAge(request.getMaxChildAge())
                .email(request.getEmail())
                .bio(request.getBio())
                .build();

        return sitterRepository.save(sitterUser);
    }

    public ParentUser signUpParent(ParentSignUpRequest request) {

        if (userRepository.existsByUserId(request.getUserId())) {
            throw new DuplicateUserIdException("이미 존재하는 아이디입니다. (" + request.getUserId() + ")");
        }

        ParentUser parentUser = ParentUser.builder()
                .name(request.getName())
                .birthAt(request.getBirthAt())
                .gender(request.getGender())
                .userId(request.getUserId())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .children(request.getChildren())
                .requirements(request.getRequirements())
                .build();

        return parentRepository.save(parentUser);
    }

    public SitterUser getSitter(String userId) {
        return sitterRepository.findByUserId(userId);
    }

    public ParentUser getParent(String userId) {
        return parentRepository.findByUserId(userId);
    }

    public UserInfoResponse getUserDetail(String userId) {

        SitterUser sitter = sitterRepository.findByUserId(userId);
        ParentUser parent = parentRepository.findByUserId(userId);

        User user = sitter != null ? sitter : parent;

        UserInfoResponse response = new UserInfoResponse();

        response.setUserNumber(user.getUserNumber());
        response.setName(user.getName());
        response.setBirthAt(user.getBirthAt());
        response.setGender(user.getGender());
        response.setUserId(user.getUserId());
        response.setEmail(user.getEmail());

        if (sitter != null) {
            response.setMinChildAge(sitter.getMinChildAge());
            response.setMaxChildAge(sitter.getMaxChildAge());
            response.setBio(sitter.getBio());
        }
        if (parent != null) {
            response.setChildren(parent.getChildren());
            response.setRequirements(parent.getRequirements());
        }

        return response;

    }

    @Transactional
    public void registerSitter(RegisterSitterRequest request, String userId) {

        if(sitterRepository.existsByUserId(userId)){
            throw new RuntimeException("이미 시터로 등록되어 있습니다.");
        }

        sitterRepository.insertOnlySitter(request.getUserNumber(), request.getMinChildAge(),
                request.getMaxChildAge(), request.getBio(), LocalDateTime.now());
    }

    @Transactional
    public void registerParent(RegisterParentRequest request, String userId) {

        if(parentRepository.existsByUserId(userId)){
            throw new RuntimeException("이미 부모로 등록되어 있습니다.");
        }

        String children;

        try {
            children = objectMapper.writeValueAsString(request.getChildren());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        parentRepository.insertOnlyParent(request.getUserNumber(), children,
                request.getRequirements(), LocalDateTime.now());
    }
}
