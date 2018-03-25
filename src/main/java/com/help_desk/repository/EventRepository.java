package com.help_desk.repository;

import com.help_desk.entity.Event;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * Repository to work with Entity Event
 */
@Repository
public interface EventRepository extends CrudRepository<Event,Integer> {
    List<Event> findByOrderByIdDesc();


}
