package io.github.stillerr.dbmonitor.businessevent.driver;

import io.github.stillerr.dbmonitor.businessevent.controller.BusinessEventController;
import io.github.stillerr.dbmonitor.businessevent.dto.BusinessEventListDTO;
import io.github.stillerr.dbmonitor.businessevent.matcher.BusinessEventDTOMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class BusinessEventDriver {

    private BusinessEventController businessEventController;

    public BusinessEventDriver(BusinessEventController businessEventController) {
        this.businessEventController = businessEventController;
    }

    public BusinessEventDriver historyIsEmpty() {
        BusinessEventListDTO list = businessEventController.list();
        Assert.assertTrue(list.size == 0);
        return this;
    }

    public BusinessEventDriver historyNotEmpty() {
        BusinessEventListDTO list = businessEventController.list();
        Assert.assertTrue(list.size != 0);
        return this;
    }

    public BusinessEventDriver newRandomEvent() {
        businessEventController.createRandom();
        return this;
    }

    public BusinessEventDriver newSpecificEvent(String event) {
        businessEventController.save(event);
        return this;
    }

    public BusinessEventDriver historyContains(String... events) {
        BusinessEventListDTO list = businessEventController.list();

        for (String event : events) {
            Assert.assertThat(list.events, Matchers.hasItem(new BusinessEventDTOMatcher(event)));
        }

        return this;
    }

}
