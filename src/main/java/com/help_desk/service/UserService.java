package com.help_desk.service;

import com.help_desk.entity.UserSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Interface to work with authorization
 */
public interface UserService extends UserDetailsService {

    UserSecurity signupUser(UserSecurity user);
    UserSecurity signupUser(UserSecurity user, Long role);

    UserSecurity getCurrentUser();

    boolean hasRole(String role);
}
