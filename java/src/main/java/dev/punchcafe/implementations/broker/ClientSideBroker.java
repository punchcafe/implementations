package dev.punchcafe.implementations.broker;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import static dev.punchcafe.implementations.broker.MessageHeaders.END_HEADER;
import static java.util.stream.Collectors.joining;

public class ClientSideBroker implements Broker {

    private String hostname;
    private int portNumber;
    private Socket connection;
    private PrintWriter clientWriter;
    private BufferedReader clientInputReader;
    private Map<Subscriber, String> subscriberIdMap = new ConcurrentHashMap<>();
    private Map<String, Subscriber> idSubscriberMap = new ConcurrentHashMap<>();
    private Thread deamonThread;

    public static void main(String[] args) throws InterruptedException {
        final var broker = Broker.newBuilder().setHostName("127.0.0.1").setPortNumber(8001).build();
        final var sub = new SubscriberImp();
        broker.subscribe(sub, "lol");
        broker.publish("lol", "lol");
        broker.publish("notlol", "notlol");
    }

    ClientSideBroker(final String hostname, final int portNumber) {
        this.hostname = hostname;
        this.portNumber = portNumber;
        try {
            this.connection = new Socket(this.hostname, this.portNumber);
            this.clientInputReader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
            this.clientWriter = new PrintWriter(this.connection.getOutputStream(), true);
        } catch (IOException ex) {
            throw new RuntimeException();
        }
        this.deamonThread = new Thread(this::deamon);
        this.deamonThread.start();
    }

    @Override
    public Broker subscribe(Subscriber subscriber, String topic) {
        var subscriberId = subscriberIdMap.get(subscriber);
        if (subscriberId == null) {
            subscriberId = UUID.randomUUID().toString();
            subscriberIdMap.put(subscriber, subscriberId);
            idSubscriberMap.put(subscriberId, subscriber);
        }
        final var subRequest = new SubscribeRequest(topic, subscriberId);
        clientWriter.println(subRequest.toString());
        return this;
    }

    @Override
    public Broker publish(String message, String topic) {
        final var publishRequest = new PublishMessage(topic, message);
        clientWriter.println(publishRequest.toString());
        return this;
    }

    private void deamon() {
        while (true) {
            try {
                System.out.println("grabbing message");
                final var lines = new ArrayList<String>();
                var line = clientInputReader.readLine();
                while (line != null && !END_HEADER.equals(line)) {
                    // TODO: make reading smarter
                    lines.add(line);
                    line = clientInputReader.readLine();
                }
                System.out.println("grabbed message");
                if (lines.size() == 0) {
                    return;
                }
                final var rawMessage = String.join("\n", lines);
                System.out.println("raw-message:");
                System.out.println(rawMessage);
                final var message = ConsumerMessage.parse(rawMessage);
                final var clientIds = message.getClientIds();
                final var messageModel = new Message(message.getBody());
                for (String clientId : clientIds) {
                    final var sub = this.idSubscriberMap.get(clientId);
                    CompletableFuture.runAsync(() -> sub.onMessage(messageModel));
                }
            } catch (IOException ex) {

            }
        }
    }
}
