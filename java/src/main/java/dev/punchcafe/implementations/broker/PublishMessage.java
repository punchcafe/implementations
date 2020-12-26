package dev.punchcafe.implementations.broker;

import static dev.punchcafe.implementations.broker.MessageHeaders.END_HEADER;
import static dev.punchcafe.implementations.broker.MessageHeaders.PUBLISH_HEADER;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class PublishMessage {

    public static PublishMessage parse(final String string){
        System.out.println("here's the string to parse:");
        System.out.println(string);
        final var lines = string.lines().filter(str -> str != null && !"".equals(str)).collect(toList());
        final var body = lines.subList(2, lines.size()).stream().collect(joining("\n"));
        return new PublishMessage(lines.get(1), body);
    }

    private String topic;
    private String body;

    public PublishMessage(final String topic, final String body){
        this.body = body;
        this.topic = topic;
    }

    public String getTopic(){
        return this.topic;
    }

    public String getBody(){
        return this.body;
    }

    @Override
    public String toString(){
        return PUBLISH_HEADER + "\n" + this.topic +"\n" + this.body + "\n" + END_HEADER;
    }

}
