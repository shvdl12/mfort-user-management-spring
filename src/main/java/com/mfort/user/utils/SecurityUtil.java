package com.mfort.user.utils;

import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class SecurityUtil {

    public String getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("No authentication information.");
        }
        return authentication.getName();
    }
}
