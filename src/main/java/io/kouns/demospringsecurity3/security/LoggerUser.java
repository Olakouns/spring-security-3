package io.kouns.demospringsecurity3.security;


import io.kouns.demospringsecurity3.entities.User;
import io.kouns.demospringsecurity3.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class LoggerUser {
    private final UserRepository userRepository;

    public LoggerUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) return null;
        return userRepository.getReferenceById(((UserPrincipal) auth.getPrincipal()).getId());
    }

    public boolean checkAuthorization(String permission) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) return false;
        for (GrantedAuthority authority : auth.getAuthorities()) {
            if (authority.getAuthority().equals(permission))
                return true;
        }
        return false;
    }
}
