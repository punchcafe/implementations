package dev.punchcafe.implementations.broker;

public class Message {
    private String body;

    public Message(final String body){
        this.body = body;
    }

    public String getBody(){
        return this.body;
    }
}
