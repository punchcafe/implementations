package dev.punchcafe.implementations.event;

import dev.punchcafe.implementations.event.Event;

public interface Subscriber {
    void publishEvent(Event event);
}
