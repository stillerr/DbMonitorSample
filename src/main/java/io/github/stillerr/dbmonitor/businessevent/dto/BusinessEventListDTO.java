package io.github.stillerr.dbmonitor.businessevent.dto;

import java.util.List;

public class BusinessEventListDTO {

    public long size;

    public List<BusinessEventDTO> events;


    public BusinessEventListDTO(List<BusinessEventDTO> events) {
        this.events = events;
        this.size = events.size();
    }

}
