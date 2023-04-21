package com.luxoft.spingsecurity.anonymousauth.security;

import com.luxoft.spingsecurity.anonymousauth.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * This service is very useful
 * - you can get current user (domain class) without static calls
 * - you can simple mock this service in unit-tests
 */
@Service
public class CurrentUserService {

    public User getCurrentUser() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // For authenticated users Principal is a UserDetailsAdapter object
        final var principal = auth.getPrincipal();
        return principal instanceof UserDetailsAdapter
                ? getUser((UserDetailsAdapter) principal)
                : SecurityConfig.anonymous();
    }


    private static User getUser(UserDetailsAdapter principal) {
        return principal.getUser();
    }
}
