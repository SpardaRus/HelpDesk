package com.help_desk.service;

import com.help_desk.entity.Role;
import com.help_desk.entity.UserSecurity;
import com.help_desk.repository.RoleRepository;
import com.help_desk.repository.UserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private static final long ROLE_USER_ID = 1L;

    @Autowired
    private UserSecurityRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurity user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Can't find user with username " + username);
        }
        return user;
    }

    @Override
    public UserSecurity signupUser(UserSecurity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findOne(ROLE_USER_ID);
        user.setRoles(Collections.singleton(userRole));
        return userRepository.save(user);
    }
    @Override
    public UserSecurity signupUser(UserSecurity user, Long role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findOne(role);
        user.setRoles(Collections.singleton(userRole));
        return userRepository.save(user);
    }

    @Override
    public UserSecurity getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        UserSecurity user = null;
        if (userDetails instanceof UserSecurity) {
            user =  (UserSecurity) userDetails;
        }
        return user;
    }

    @Override
    public boolean hasRole(String role) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            hasRole = authority.getAuthority().equals(role);
            if (hasRole) {
                break;
            }
        }
        return hasRole;
    }
}