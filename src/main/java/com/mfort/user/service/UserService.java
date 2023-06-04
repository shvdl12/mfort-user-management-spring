package com.mfort.user.service;

import com.mfort.user.exception.DuplicateUserIdException;
import com.mfort.user.model.domain.ParentUser;
import com.mfort.user.model.domain.SitterUser;
import com.mfort.user.model.domain.User;
import com.mfort.user.model.request.ParentSignUpRequest;
import com.mfort.user.model.request.SitterSignUpRequest;
import com.mfort.user.model.response.UserInfoResponse;
import com.mfort.user.repository.ParentRepository;
import com.mfort.user.repository.SitterRepository;
import com.mfort.user.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ParentRepository parentRepository;
    private final SitterRepository sitterRepository;
    private final SecurityUtil securityUtil;
    private final PasswordEncoder passwordEncoder;


    public SitterUser signUpSitter(SitterSignUpRequest request) {

        if (sitterRepository.existsByUserId(request.getUserId())) {
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

        if (parentRepository.existsByUserId(request.getUserId())) {
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
}
