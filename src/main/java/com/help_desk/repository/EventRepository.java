package com.help_desk.repository;

import com.help_desk.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event,Integer> {
    List<Event> findByOrderByIdDesc();
}
