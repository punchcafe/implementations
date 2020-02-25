package dev.punchcafe.implementations.event;

public interface Subscriber {
    void publishEvent(Event event);
}
