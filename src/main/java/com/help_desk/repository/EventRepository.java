package com.help_desk.repository;

import com.help_desk.entity.Event;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event,Integer> {
    Event findById(Integer id);
    List<Event> findByOrderByIdDesc();

    @Modifying
    @Transactional
    @Query(value = "UPDATE Event e " +
            "SET " +
            "   e.id_user=:idu, " +
            "   e.id_admin=:ida, " +
            "   e.description=:description " +
            "   e.date=:date, " +
            "   e.comment=:com, " +
            "   e.id_quality=:idq, " +
            "   e.id_status=:ids " +
            "WHERE e.id=:id", nativeQuery = true)
    int setEvent(@Param("id") Integer id,
                 @Param("idu") Integer idu,
                 @Param("ida") Integer ida,
                 @Param("description") String description,
                 @Param("date") String date,
                 @Param("com") String com,
                 @Param("idq") Integer idq,
                 @Param("ids") Integer ids
                 );

}
