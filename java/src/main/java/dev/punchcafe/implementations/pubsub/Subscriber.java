package dev.punchcafe.implementations.pubsub;

public interface Subscriber {

    void consumeMessage(Message message);
}
