package io.github.stillerr.dbmonitor.businessevent.repository;

import io.github.stillerr.dbmonitor.businessevent.domain.BusinessEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessEventDAO extends CrudRepository<BusinessEvent, Long> {

    @Override
    @Query("SELECT b FROM BusinessEvent b ORDER BY added DESC")
    Iterable<BusinessEvent> findAll();
}
