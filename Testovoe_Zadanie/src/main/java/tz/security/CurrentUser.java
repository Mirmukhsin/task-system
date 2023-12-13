package tz.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import tz.dto.AuthUser;

@Component
public class CurrentUser {
    public AuthUser getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            return userDetails.getUser();
        } else {
            return null;
        }
    }
}
