package io.github.stillerr.dbmonitor.businessevent.controller;

import io.github.stillerr.dbmonitor.AbstractEndToEndTest;
import io.github.stillerr.dbmonitor.businessevent.driver.BusinessEventDriver;
import io.github.stillerr.dbmonitor.businessevent.dto.BusinessEventListDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessEventEndToEndTest extends AbstractEndToEndTest {

    @Autowired
    private BusinessEventController businessEventController;

    @Test
    public void canSaveRandomEvent() {
        new BusinessEventDriver(businessEventController)
                .historyIsEmpty()
                .newRandomEvent()
                .historyNotEmpty();
    }

    @Test
    public void canSaveSpecificEvents() {
        new BusinessEventDriver(businessEventController)
                .historyIsEmpty()
                .newSpecificEvent("hello")
                .newSpecificEvent("world")
                .historyNotEmpty()
                .historyContains("hello", "world");
    }


}