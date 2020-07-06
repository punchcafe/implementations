package dev.punchcafe.implementations.pubsub;

public interface BrokerClient {
    void publishMessage(String topic, Message message);
}
