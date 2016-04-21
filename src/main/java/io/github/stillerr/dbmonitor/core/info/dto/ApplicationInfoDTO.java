package io.github.stillerr.dbmonitor.core.info.dto;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class ApplicationInfoDTO {

    public String version;
    public String createdBy;

    public ApplicationInfoDTO(String version, String createdBy) {
        this.version = version;
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("version", version)
                .add("createdBy", createdBy)
                .toString();
    }
}
