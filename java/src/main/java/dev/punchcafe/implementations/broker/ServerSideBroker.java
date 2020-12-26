package dev.punchcafe.implementations.broker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import static dev.punchcafe.implementations.broker.MessageHeaders.*;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public class ServerSideBroker implements Runnable {

    public static void main(String[] args) {
        final var broker = new ServerSideBroker(8001);
        broker.run();
    }

    private int portNumber;
    private ServerSocket serverSocket;
    // TODO: handle multiple clients
    private List<PrintWriter> clientWriters = new ArrayList<>();
    private Map<String, List<String>> topicToSubscribers = new ConcurrentHashMap<>();

    private synchronized List<PrintWriter> getClientWriters(){
        return List.copyOf(this.clientWriters);
    }

    private synchronized void addClientWriter(final PrintWriter clientWriter){
        this.clientWriters.add(clientWriter);
    }

    private ServerSideBroker(final int portNumber) {
        try {
            this.serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void handleConnection(final Socket clientSocket) throws IOException {
        // TODO: handle removal on connection
        final PrintWriter clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        final BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.addClientWriter(clientWriter);
        while (true){
            final var lines = new ArrayList<String>();
            var line = clientReader.readLine();
            while(line != null && !END_HEADER.equals(line)){
                // TODO: make reading smarter
                lines.add(line);
                line = clientReader.readLine();
            }
            if(lines.size() == 0){
                return;
            }
            System.out.println("lines:");
            System.out.println(lines);
            final var message = String.join("\n",lines);
            switch (lines.get(0)){
                case SUBSCRIBE_HEADER:
                    final var request = SubscribeRequest.parse(message);
                    CompletableFuture.runAsync(() -> this.handleSubscribeRequest(request));
                    break;
                case PUBLISH_HEADER:
                    final var publishMessage = PublishMessage.parse(message);
                    CompletableFuture.runAsync(() -> this.handlePublishRequest(publishMessage));
                    break;
                default:
                    return;
            }
        }

    }

    private void handleSubscribeRequest(final SubscribeRequest request) {
        final var subscriberList = ofNullable(topicToSubscribers.get(request.getTopic())).orElseGet(ArrayList::new);
        subscriberList.add(request.getSubscriberId());
        this.topicToSubscribers.put(request.getTopic(), subscriberList);
    }

    private void handlePublishRequest(final PublishMessage request) {
        System.out.println("handling publish request:");
        final var subscribers = topicToSubscribers.get(request.getTopic());
        if (subscribers == null || subscribers.isEmpty()) {
            return;
        }
        final var consumerMessage = new ConsumerMessage(subscribers, request.getBody());
        System.out.println(consumerMessage);
        for (PrintWriter writer : this.getClientWriters()) {
            writer.println(consumerMessage.toString());
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                var clientSocket = this.serverSocket.accept();
                this.handleConnection(clientSocket);
            } catch (IOException ex) {

            }
        }
    }
}
