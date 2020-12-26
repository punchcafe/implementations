package dev.punchcafe.implementations.broker;

public class SubscriberImp implements Subscriber {

    @Override
    public void onMessage(Message message) {
        System.out.println("I have retrieved the message!");
        System.out.println(message.getBody());
    }
}
