package dev.punchcafe.implementations.broker;

import java.util.List;

import static dev.punchcafe.implementations.broker.MessageHeaders.CONSUMER_HEADER;
import static dev.punchcafe.implementations.broker.MessageHeaders.END_HEADER;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ConsumerMessage {

    public static ConsumerMessage parse(final String string) {
        final var lines = string.lines().filter(str -> str != null || !str.isEmpty()).collect(toList());
        final var clientIds = List.of(lines.get(1).split(","));
        final var body = lines.subList(2, lines.size()).stream().collect(joining("\n"));
        return new ConsumerMessage(clientIds, body);
    }

    private List<String> clientIds;
    private String body;

    public ConsumerMessage(final List<String> clientIds, final String body) {
        this.body = body;
        this.clientIds = clientIds;
    }

    @Override
    public String toString() {
        return CONSUMER_HEADER + "\n"
                + String.join(",", this.clientIds)
                + "\n" + this.body + "\n" + END_HEADER;
    }

    public List<String> getClientIds(){
        return this.clientIds;
    }

    public String getBody(){
        return this.body;
    }
}
