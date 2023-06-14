package com.mfort.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfort.user.common.model.ResponseCode;
import com.mfort.user.exception.ApiException;
import com.mfort.user.model.domain.SitterUser;
import com.mfort.user.model.domain.ParentUser;
import com.mfort.user.model.domain.User;
import com.mfort.user.model.request.*;
import com.mfort.user.model.response.UserInfoResponse;
import com.mfort.user.repository.ParentRepository;
import com.mfort.user.repository.SitterRepository;
import com.mfort.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
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
            throw new ApiException(ResponseCode.DUPLICATE_USER_ID);
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .birthAt(request.getBirthAt())
                .gender(request.getGender())
                .userId(request.getUserId())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        SitterUser sitterUser = SitterUser.builder()
                .user(user)
                .minChildAge(request.getMinChildAge())
                .maxChildAge(request.getMaxChildAge())
                .bio(request.getBio())
                .build();

        return sitterRepository.save(sitterUser);
    }

    public ParentUser signUpParent(ParentSignUpRequest request) {

        if (userRepository.existsByUserId(request.getUserId())) {
            throw new ApiException(ResponseCode.DUPLICATE_USER_ID);
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .birthAt(request.getBirthAt())
                .gender(request.getGender())
                .userId(request.getUserId())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        ParentUser parentUser = ParentUser.builder()
                .user(user)
                .children(request.getChildren())
                .requirements(request.getRequirements())
                .build();

        return parentRepository.save(parentUser);
    }

    public SitterUser getSitter(String userId) {
        return sitterRepository.findByUserUserId(userId);
    }

    public ParentUser getParent(String userId) {
        return parentRepository.findByUserUserId(userId);
    }

    public UserInfoResponse getUserDetail(String userId) {

        SitterUser sitter = sitterRepository.findByUserUserId(userId);
        ParentUser parent = parentRepository.findByUserUserId(userId);

        User user = (sitter != null) ? sitter.getUser() : (parent != null) ? parent.getUser() : null;

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

        //TODO 에러 정의하기
        if (sitterRepository.existsByUserUserId(userId)) {
            throw new ApiException(ResponseCode.ALREADY_REGISTERED_SITTER);
        }

        User user = userRepository.findByUserId(userId);

        sitterRepository.insertOnlySitter(user.getUserNumber(), request.getMinChildAge(),
                request.getMaxChildAge(), request.getBio(), LocalDateTime.now());
    }

    @Transactional
    public void registerParent(RegisterParentRequest request, String userId) {

        //TODO 에러 정의하기
        if (parentRepository.existsByUserUserId(userId)) {
            throw new ApiException(ResponseCode.ALREADY_REGISTERED_PARENT);
        }

        String children;

        try {
            children = objectMapper.writeValueAsString(request.getChildren());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        User user = userRepository.findByUserId(userId);

        parentRepository.insertOnlyParent(user.getUserNumber(), children,
                request.getRequirements(), LocalDateTime.now());
    }

    public UserInfoResponse updateSitter(UpdateSitterRequest request, String userId) {

        SitterUser sitter = Optional.ofNullable(sitterRepository.findByUserUserId(userId))
                .orElseThrow(() -> new ApiException(ResponseCode.SITTER_INFO_NOT_FOUND));

        if (request.getPassword() != null) {
            request.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        sitter.updateSitter(request);

        SitterUser result = sitterRepository.save(sitter);

        UserInfoResponse response = new UserInfoResponse();

        response.setUserNumber(result.getUser().getUserNumber());
        response.setName(result.getUser().getName());
        response.setBirthAt(result.getUser().getBirthAt());
        response.setGender(result.getUser().getGender());
        response.setUserId(result.getUser().getUserId());
        response.setEmail(result.getUser().getEmail());

        response.setMinChildAge(result.getMinChildAge());
        response.setMaxChildAge(result.getMaxChildAge());
        response.setBio(result.getBio());

        return response;
    }

    public UserInfoResponse updateParent(UpdateParentRequest request, String userId) {

        ParentUser parent = Optional.ofNullable(parentRepository.findByUserUserId(userId))
                .orElseThrow(() -> new ApiException(ResponseCode.PARENT_INFO_NOT_FOUND));

        if (request.getPassword() != null) {
            request.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        parent.updateParent(request);

        ParentUser result = parentRepository.save(parent);

        UserInfoResponse response = new UserInfoResponse();

        response.setUserNumber(result.getUser().getUserNumber());
        response.setName(result.getUser().getName());
        response.setBirthAt(result.getUser().getBirthAt());
        response.setGender(result.getUser().getGender());
        response.setUserId(result.getUser().getUserId());
        response.setEmail(result.getUser().getEmail());

        response.setChildren(result.getChildren());
        response.setRequirements(result.getRequirements());

        return response;
    }
}
