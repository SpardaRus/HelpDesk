package com.help_desk.repository;

import com.help_desk.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.beans.Transient;
/**
 * Repository to work with Entity User
 */
@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    @Query(value = "select * from user u where u.id_auth=:id_auth",nativeQuery = true)
    User findById_auth(@Param("id_auth") int id_auth);
    }
