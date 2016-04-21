package io.github.stillerr.dbmonitor;

import io.github.stillerr.dbmonitor.businessevent.repository.BusinessEventDAO;
import io.github.stillerr.dbmonitor.eventlistener.repository.ProcessedBusinessEventDAO;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public abstract class AbstractEndToEndTest {

    @Autowired
    private BusinessEventDAO businessEventDAO;

    @Autowired
    private ProcessedBusinessEventDAO processedBusinessEventDAO;

    @Before
    public void setUp() throws Exception {
        processedBusinessEventDAO.deleteAll();
        businessEventDAO.deleteAll();
    }
}
