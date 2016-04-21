package io.github.stillerr.dbmonitor.eventlistener.repository;

import io.github.stillerr.dbmonitor.eventlistener.domain.ProcessedBusinessEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedBusinessEventDAO extends CrudRepository<ProcessedBusinessEvent, Long> {
}
