package dev.punchcafe.implementations.event;

import java.util.ArrayList;
import java.util.List;

public class EventBus {
    private List<Subscriber> subscribers = new ArrayList<>();

    public EventBus registerSubscriber(Subscriber sub){
        subscribers.add(sub);
        return this;
    }

    public EventBus publishEvent(Event e){
        subscribers.stream().forEach(subscriber -> subscriber.publishEvent(e));
        return this;
    }
}
