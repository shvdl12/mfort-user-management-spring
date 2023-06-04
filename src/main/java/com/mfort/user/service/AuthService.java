package com.mfort.user.service;

import com.mfort.user.exception.UserNotFoundException;
import com.mfort.user.jwt.JwtTokenProvider;
import com.mfort.user.jwt.model.JwtToken;
import com.mfort.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public JwtToken getToken(String userId, String password) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        JwtToken token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) {
        return Optional.ofNullable(userRepository.findByUserId(userId))
                .orElseThrow(() -> new UsernameNotFoundException(userId));
    }
}
