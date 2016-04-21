package io.github.stillerr.dbmonitor.eventlistener.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
@Table(name = "PROCESSED_BUSINESS_EVENTS")
public class ProcessedBusinessEvent {

    @Id
    private long id;


    public ProcessedBusinessEvent() {
    }

    public static ProcessedBusinessEvent newId(long id) {
        ProcessedBusinessEvent evt = new ProcessedBusinessEvent();
        evt.id = id;
        return evt;
    }

    public long getId() {
        return id;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
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
        ProcessedBusinessEvent that = (ProcessedBusinessEvent) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
