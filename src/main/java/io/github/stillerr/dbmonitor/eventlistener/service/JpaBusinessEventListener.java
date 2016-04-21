package io.github.stillerr.dbmonitor.eventlistener.service;

import io.github.stillerr.dbmonitor.businessevent.dto.BusinessEventDTO;
import io.github.stillerr.dbmonitor.eventlistener.domain.ProcessedBusinessEvent;
import io.github.stillerr.dbmonitor.eventlistener.repository.ProcessedBusinessEventDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JpaBusinessEventListener implements MessageHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private ProcessedBusinessEventDAO processedBusinessEventDAO;

    private BusinessEventSender businessEventSender;


    @Autowired
    public JpaBusinessEventListener(ProcessedBusinessEventDAO processedBusinessEventDAO, BusinessEventSender businessEventSender) {
        this.processedBusinessEventDAO = processedBusinessEventDAO;
        this.businessEventSender = businessEventSender;
    }


    @Override
    @SuppressWarnings("unchecked")
    public void handleMessage(Message<?> message) throws MessagingException {

        List<Map> payload = (List<Map>)message.getPayload();

        for (Map item : payload) {
            handleMessage(item);
        }

    }

    private void handleMessage(Map<String, Object> item) {

        BusinessEventDTO dto = convert(item);

        try {
            send(dto);
            processedBusinessEventDAO.save(ProcessedBusinessEvent.newId(dto.id));
        } catch (Exception e) {
            logger.error("Send failed!", e);
        }
    }

    private void send(BusinessEventDTO businessEvent) throws Exception {
        logger.info("BusinessEvent: " + businessEvent);
        businessEventSender.send(businessEvent);
    }

    // TODO should replace with a better (generic) solution
    private BusinessEventDTO convert(Map<String, Object> item) {
        final String id = "ID";
        final String EVENT = "EVENT";
        final String ADDED = "ADDED";
        return new BusinessEventDTO((Long)item.get(id), (String)item.get(EVENT), (Date)item.get(ADDED));
    }

}
