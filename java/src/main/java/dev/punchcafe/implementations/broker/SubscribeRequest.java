package dev.punchcafe.implementations.broker;

import java.io.Serializable;
import java.util.stream.Collectors;

import static dev.punchcafe.implementations.broker.MessageHeaders.END_HEADER;
import static dev.punchcafe.implementations.broker.MessageHeaders.SUBSCRIBE_HEADER;

public class SubscribeRequest implements Serializable {

    public static SubscribeRequest parse(final String request){
        final var lines = request.lines().filter(str -> str != null || str.equals("")).collect(Collectors.toList());
        if(lines.size() != 3 || !lines.get(0).equals("SUBSCRIBER")){
            throw new RuntimeException();
        }
        return new SubscribeRequest(lines.get(2), lines.get(1));
    }

    private String subscriberId;
    private String topic;

    public SubscribeRequest(final String topic, final String subscriberId){
        this.topic = topic;
        this.subscriberId = subscriberId;
    }

    public String getTopic(){
        return this.topic;
    }

    public String getSubscriberId(){
        return this.subscriberId;
    }

    @Override
    public String toString(){
        return SUBSCRIBE_HEADER + "\n" + this.subscriberId + "\n" + topic + "\n" + END_HEADER;
    }

}
