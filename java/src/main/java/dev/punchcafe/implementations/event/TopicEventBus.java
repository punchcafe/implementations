package dev.punchcafe.implementations.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TopicEventBus {
    private Map<String,List<Subscriber>> topicSubscribers = new HashMap<>();

    public TopicEventBus registerSubscriber(Subscriber sub, String topic){
        List<Subscriber> subscribers = Optional.ofNullable(topicSubscribers.get(topic)).orElse(new ArrayList<Subscriber>());
        subscribers.add(sub);
        topicSubscribers.put(topic, subscribers);
        return this;
    }

    public TopicEventBus publishEvent(Event e, String topic){
        //subscribers.stream().forEach(subscriber -> subscriber.publishEvent(e));
        return this;
    }
}
