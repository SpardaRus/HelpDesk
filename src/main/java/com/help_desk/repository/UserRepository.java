package com.help_desk.repository;

import com.help_desk.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends CrudRepository<User,Integer> {

}
