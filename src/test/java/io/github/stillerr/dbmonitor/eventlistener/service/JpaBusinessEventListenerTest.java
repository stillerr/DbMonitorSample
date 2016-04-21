package io.github.stillerr.dbmonitor.eventlistener.service;

import com.google.common.collect.Lists;
import io.github.stillerr.dbmonitor.businessevent.dto.BusinessEventDTO;
import io.github.stillerr.dbmonitor.eventlistener.domain.ProcessedBusinessEvent;
import io.github.stillerr.dbmonitor.eventlistener.repository.ProcessedBusinessEventDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class JpaBusinessEventListenerTest {

    @Mock
    private ProcessedBusinessEventDAO processedBusinessEventDAO;

    @Mock
    private BusinessEventSender businessEventSender;

    @Test
    public void canSend() throws Exception {

        JpaBusinessEventListener jpaBusinessEventListener = new JpaBusinessEventListener(processedBusinessEventDAO, businessEventSender);

        final long id = 3L;
        final String event = "hello";
        final Date added = new Date();

        jpaBusinessEventListener.handleMessage(buildSampleMessage(id, event, added));

        Mockito.verify(businessEventSender, new Times(1)).send(new BusinessEventDTO(id, event, added));
        Mockito.verify(processedBusinessEventDAO, new Times(1)).save(ProcessedBusinessEvent.newId(id));

    }

    @SuppressWarnings("unchecked")
    private Message<List<Map<String, Object>>> buildSampleMessage(long id, String event, Date added) {
        MessageBuilder<List<Map<String, Object>>> message = MessageBuilder.withPayload(Lists.newArrayList(
                createMessage(id, event, added)
        ));
        return message.build();
    }

    private Map<String, Object> createMessage(long id, String event, Date added) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("ID", id);
        map.put("EVENT", event);
        map.put("ADDED", added);
        return map;
    }

}