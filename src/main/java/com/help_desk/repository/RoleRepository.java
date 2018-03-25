package com.help_desk.repository;

import com.help_desk.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository to work with Entity Role
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
