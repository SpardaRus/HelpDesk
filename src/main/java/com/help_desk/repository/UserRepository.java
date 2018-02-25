package com.help_desk.repository;

import com.help_desk.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    User findById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE User u SET u.name=:name, u.address=:address WHERE u.id=:id", nativeQuery = true)
    int setUser(@Param("id") Integer id,@Param("name") String name, @Param("address") String address);
}
