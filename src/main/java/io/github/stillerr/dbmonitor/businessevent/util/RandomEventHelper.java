package io.github.stillerr.dbmonitor.businessevent.util;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

public class RandomEventHelper {

    private static List<String> events = Lists.newArrayList(
            "I'm not familiar with it so I didn't fix it in case I made it worse",
            "Oh, that was only supposed to be a placeholder",
            "I broke that deliberately to do some testing",
            "You must have the wrong version",
            "The project manager told me to do it that way",
            "We spent three months debugging it because we only had one month to build it",
            "I must have been stress testing our production server",
            "I forgot to commit the code that fixes that",
            "Oh, you said you DIDN'T want that to happen?",
            "That's already fixed it just hasn't taken effect yet"
    );

    public static String createRandomEventMessage() {
        int index = new Random().nextInt(events.size());
        return events.get(index);
    }

}
