package io.github.stillerr.dbmonitor.businessevent.controller;

import io.github.stillerr.dbmonitor.businessevent.dto.BusinessEventCreatedDTO;
import io.github.stillerr.dbmonitor.businessevent.dto.BusinessEventListDTO;
import io.github.stillerr.dbmonitor.businessevent.service.BusinessEventCommandService;
import io.github.stillerr.dbmonitor.businessevent.service.BusinessEventQueryService;
import io.github.stillerr.dbmonitor.businessevent.util.RandomEventHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static io.github.stillerr.dbmonitor.businessevent.util.RandomEventHelper.createRandomEventMessage;

@RequestMapping("/event")
@RestController
public class BusinessEventController {

    private BusinessEventCommandService commandService;
    private BusinessEventQueryService queryService;

    @Autowired
    public BusinessEventController(BusinessEventCommandService commandService, BusinessEventQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @RequestMapping(value = "random", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BusinessEventCreatedDTO createRandom() {
        String randomEventMessage = createRandomEventMessage();
        commandService.save(randomEventMessage);
        return new BusinessEventCreatedDTO(randomEventMessage);
    }

    @RequestMapping(value = "save/{event}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BusinessEventCreatedDTO save(@PathVariable(value = "event") String event) {
        commandService.save(event);
        return new BusinessEventCreatedDTO(event);
    }

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BusinessEventListDTO list() {
        return queryService.list();
    }

}
