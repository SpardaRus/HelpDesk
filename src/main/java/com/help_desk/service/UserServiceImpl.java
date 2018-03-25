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

/**
 * Implementation of the interface to work with authorization
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * The default role 'ROLE_USER'
     */
    private static final long ROLE_USER_ID = 1L;
    /**
     * The repository is a table of username/password
     */
    @Autowired
    private UserSecurityRepository userRepository;
    /**
     * The object to encode password
     */
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * The repository is a table of roles
     */
    @Autowired
    private RoleRepository roleRepository;

    /**
     * Search user data by name
     * @param username username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurity user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Can't find user with username " + username);
        }
        return user;
    }

    /**
     * Registering a user with the default role
     * @param user user
     * @return
     */
    @Override
    public UserSecurity signupUser(UserSecurity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findOne(ROLE_USER_ID);
        user.setRoles(Collections.singleton(userRole));
        return userRepository.save(user);
    }
    /**
     * Registering a user with the ROLE_ADMIN role
     * @param user user
     * @return
     */
    @Override
    public UserSecurity signupUser(UserSecurity user, Long role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findOne(role);
        user.setRoles(Collections.singleton(userRole));
        return userRepository.save(user);
    }

    /**
     * Retrieving user data for a given session
     * @return user
     */
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

    /**
     * Checking the user role of a given session
     * @param role Name role
     * @return
     */
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