package io.github.stillerr.dbmonitor.businessevent.matcher;

import io.github.stillerr.dbmonitor.businessevent.dto.BusinessEventDTO;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.core.IsAnything;

public class BusinessEventDTOMatcher extends TypeSafeDiagnosingMatcher<BusinessEventDTO> {

    public Matcher<String> event = new IsAnything<>();

    public BusinessEventDTOMatcher(String event) {
        this.event = Matchers.is(event);
    }

    @Override
    protected boolean matchesSafely(BusinessEventDTO businessEventDTO, Description description) {
        return event.matches(businessEventDTO.event);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("event: ")
                .appendDescriptionOf(this.event);
    }
}
