package com.help_desk.repository;

import com.help_desk.entity.UserSecurity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository to work with Entity UserSecurity
 */
@Repository
public interface UserSecurityRepository extends CrudRepository<UserSecurity, Integer> {

    UserSecurity findByUsername(String username);
}
