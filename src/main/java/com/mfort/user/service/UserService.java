package com.mfort.user.service;

import com.mfort.user.exception.DuplicateUserIdException;
import com.mfort.user.model.domain.Parent;
import com.mfort.user.model.domain.Sitter;
import com.mfort.user.model.request.ParentSignUpRequest;
import com.mfort.user.model.request.SitterSignUpRequest;
import com.mfort.user.repository.ParentRepository;
import com.mfort.user.repository.SitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ParentRepository parentRepository;
    private final SitterRepository sitterRepository;
    private final PasswordEncoder passwordEncoder;


    public Sitter signUpSitter(SitterSignUpRequest request) {

        if(sitterRepository.existsByUserId(request.getUserId())) {
            throw new DuplicateUserIdException("이미 존재하는 아이디입니다. (" + request.getUserId() + ")");
        }

        Sitter sitter = Sitter.builder()
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

        return sitterRepository.save(sitter);
    }

    public Parent signUpParent(ParentSignUpRequest request) {

        if(parentRepository.existsByUserId(request.getUserId())) {
            throw new DuplicateUserIdException("이미 존재하는 아이디입니다. (" + request.getUserId() + ")");
        }

        Parent parent = Parent.builder()
                .name(request.getName())
                .birthAt(request.getBirthAt())
                .gender(request.getGender())
                .userId(request.getUserId())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .children(request.getChildren())
                .requirements(request.getRequirements())
                .build();

        return parentRepository.save(parent);
    }

    public Sitter getSitter(String userId) {
        return sitterRepository.findByUserId(userId);
    }

    public Parent getParent(String userId) {
        return parentRepository.findByUserId(userId);
    }
}
