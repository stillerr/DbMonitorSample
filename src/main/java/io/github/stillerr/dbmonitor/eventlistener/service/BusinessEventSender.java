package io.github.stillerr.dbmonitor.eventlistener.service;

import io.github.stillerr.dbmonitor.businessevent.dto.BusinessEventDTO;
import io.github.stillerr.dbmonitor.core.websocket.WebsocketConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class BusinessEventSender {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public static final String TOPIC_EVENTS = WebsocketConstants.TOPIC + "/events";

    private SimpMessagingTemplate template;


    @Autowired
    public BusinessEventSender(SimpMessagingTemplate template) {
        this.template = template;
    }


    public void send(BusinessEventDTO businessEvent) throws Exception {
        logger.info("Event sent: " + businessEvent);
        template.convertAndSend(TOPIC_EVENTS, businessEvent);
    }

}
