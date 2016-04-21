package io.github.stillerr.dbmonitor.businessevent.domain;

import com.google.common.base.MoreObjects;
import io.github.stillerr.dbmonitor.businessevent.dto.BusinessEventDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "BUSINESS_EVENTS")
public class BusinessEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "EVENT", nullable = true)
    private String event;

    @Column(name = "ADDED", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date added;


    public BusinessEvent() {
    }

    public static BusinessEvent createNew(String event) {
        BusinessEvent evt = new BusinessEvent();
        evt.event = event;
        evt.added = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        return evt;
    }


    public BusinessEventDTO toDTO() {
        return new BusinessEventDTO(id, event, added);
    }

    public long getId() {
        return id;
    }

    public String getEvent() {
        return event;
    }

    public Date getAdded() {
        return added;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("event", event)
                .add("added", added)
                .toString();
    }
}
