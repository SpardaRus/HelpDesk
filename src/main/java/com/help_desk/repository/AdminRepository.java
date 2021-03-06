package com.help_desk.repository;

import com.help_desk.entity.Admin;
import com.help_desk.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository to work with Entity Admin
 */
@Repository
public interface AdminRepository extends CrudRepository<Admin,Integer> {

}
