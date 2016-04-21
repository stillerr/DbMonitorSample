package io.github.stillerr.dbmonitor.businessevent.service;

import com.google.common.collect.Lists;
import io.github.stillerr.dbmonitor.businessevent.domain.BusinessEvent;
import io.github.stillerr.dbmonitor.businessevent.dto.BusinessEventDTO;
import io.github.stillerr.dbmonitor.businessevent.dto.BusinessEventListDTO;
import io.github.stillerr.dbmonitor.businessevent.repository.BusinessEventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BusinessEventQueryService {

    private BusinessEventDAO dao;

    @Autowired
    public BusinessEventQueryService(BusinessEventDAO dao) {
        this.dao = dao;
    }


    public BusinessEventListDTO list() {

        List<BusinessEvent> events = Lists.newArrayList(dao.findAll());
        List<BusinessEventDTO> dtos = events.stream().map(BusinessEvent::toDTO).collect(Collectors.toList());

        return new BusinessEventListDTO(dtos);
    }

}
