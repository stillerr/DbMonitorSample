package io.github.stillerr.dbmonitor.core.info.service;

import io.github.stillerr.dbmonitor.core.info.dto.ApplicationInfoDTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class ApplicationInfoService {

    // should read from MANIFEST.MF

    @Value("${app.version}")
    private String version;

    @Value("${app.createdBy}")
    private String createdBy = "";

    public ApplicationInfoDTO toApplicationInfoDTO() {
        return new ApplicationInfoDTO(version, createdBy);
    }
}
