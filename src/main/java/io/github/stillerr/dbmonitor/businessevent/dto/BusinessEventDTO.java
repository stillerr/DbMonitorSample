package io.github.stillerr.dbmonitor.businessevent.dto;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Date;

public class BusinessEventDTO {

    public long id;

    public String event;

    public Date added;


    public BusinessEventDTO() {
    }

    public BusinessEventDTO(long id, String event, Date added) {
        this.id = id;
        this.event = event;
        this.added = added;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("event", event)
                .add("added", added)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BusinessEventDTO that = (BusinessEventDTO) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(event, that.event) &&
                Objects.equal(added, that.added);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, event, added);
    }
}
