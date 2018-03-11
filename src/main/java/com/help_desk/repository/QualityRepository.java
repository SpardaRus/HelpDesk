package com.help_desk.repository;

import com.help_desk.entity.Quality;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualityRepository extends CrudRepository<Quality,Integer> {

}
