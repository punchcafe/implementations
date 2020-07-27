package dev.punchcafe.implementations.event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class EventBusConcurrent {
    private List<Subscriber> subscribers = new ArrayList<>();

    public EventBusConcurrent registerSubscriber(Subscriber sub){
        subscribers.add(sub);
        return this;
    }

    public EventBusConcurrent publishEventBlocking(Event e) throws ExecutionException, InterruptedException {
        return publishEvent(e, true);
    }

    public EventBusConcurrent publishEventNonBlocking(Event e) throws ExecutionException, InterruptedException {
        return publishEvent(e, false);
    }

    private EventBusConcurrent publishEvent(Event e, boolean blocking) throws ExecutionException, InterruptedException {
        final List<CompletableFuture<Subscriber>> awaitingSubs = subscribers.stream().map(subscriber -> {
            final Supplier<Subscriber> method = () -> {
                subscriber.publishEvent(e);
                return subscriber;
            };
            return CompletableFuture.supplyAsync(method);
        }).collect(Collectors.toList());

        if(blocking) {
            // Waits for all to be activated
            CompletableFuture.allOf(awaitingSubs.toArray(new CompletableFuture<?>[]{})).get();
        }
        return this;
    }
}
