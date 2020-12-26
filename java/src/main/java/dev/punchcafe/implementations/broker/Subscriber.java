package dev.punchcafe.implementations.broker;

public interface Subscriber {
    void onMessage(Message message);
}
