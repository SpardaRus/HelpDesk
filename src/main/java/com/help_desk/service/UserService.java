package com.help_desk.service;

import com.help_desk.entity.UserSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserSecurity signupUser(UserSecurity user);

    UserSecurity getCurrentUser();

    boolean hasRole(String role);
}
