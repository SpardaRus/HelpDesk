package com.help_desk.repository;

import com.help_desk.entity.Admin;
import com.help_desk.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdminRepository extends CrudRepository<Admin,Integer> {
    Admin findById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Admin a SET a.name=:name WHERE a.id=:id", nativeQuery = true)
    int setAdmin(@Param("id") Integer id, @Param("name") String nameS);
}
