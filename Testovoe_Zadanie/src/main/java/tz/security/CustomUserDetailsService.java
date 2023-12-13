package tz.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import tz.dto.AuthUser;
import tz.exception.UserNotFoundException;
import tz.service.UserService;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        AuthUser user = userService.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
        return new CustomUserDetails(user);
    }
}
