package io.github.stillerr.dbmonitor.core.info.controller;

import io.github.stillerr.dbmonitor.core.info.dto.ApplicationInfoDTO;
import io.github.stillerr.dbmonitor.core.info.service.ApplicationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/info")
@RestController
public class ApplicationInfoController {

    @Autowired
    private ApplicationInfoService infoService;


    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ApplicationInfoDTO info() {
        return infoService.toApplicationInfoDTO();
    }

}
