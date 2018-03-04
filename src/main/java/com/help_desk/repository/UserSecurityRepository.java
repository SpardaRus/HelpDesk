package com.help_desk.repository;

import com.help_desk.entity.UserSecurity;
import org.springframework.data.repository.CrudRepository;

public interface UserSecurityRepository extends CrudRepository<UserSecurity, Long> {

    UserSecurity findByUsername(String username);
}
