package io.github.stillerr.dbmonitor.businessevent.service;

import io.github.stillerr.dbmonitor.businessevent.domain.BusinessEvent;
import io.github.stillerr.dbmonitor.businessevent.repository.BusinessEventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessEventCommandService {

    private BusinessEventDAO dao;


    public BusinessEventCommandService() {
    }

    @Autowired
    public BusinessEventCommandService(BusinessEventDAO dao) {
        this.dao = dao;
    }


    public void save(String event) {
        dao.save(BusinessEvent.createNew(event));
    }

}
