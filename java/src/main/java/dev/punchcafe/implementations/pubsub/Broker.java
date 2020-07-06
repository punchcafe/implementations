package dev.punchcafe.implementations.pubsub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class Broker {

    Map<String, List<Subscriber>> subscribers = new HashMap<>();

    void addSubscriber(Subscriber subscriber, String topic){
        List<Subscriber> subscribers;
        if(this.subscribers.get(topic) != null){
            subscribers = this.subscribers.get(topic);
        } else {
            subscribers = new ArrayList<>();
        }
        subscribers.add(subscriber);
        this.subscribers.put(topic, subscribers);
    }

    void publishMessage(String topic, Message message){
        List<Subscriber> targetedSubscribers = subscribers.get(topic);
        if(targetedSubscribers == null){
            return;
        }
        for(Subscriber subscriber : targetedSubscribers){
            CompletableFuture.runAsync(() -> subscriber.consumeMessage(message));
        }

    }
}
