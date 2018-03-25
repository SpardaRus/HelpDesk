package com.help_desk.repository;

import com.help_desk.entity.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository to work with Entity Status
 */
@Repository
public interface StatusRepository extends CrudRepository<Status,Integer> {
}
