package dev.punchcafe.implementations.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

class EventBusConcurrentTest {

    private EventBus nonConcurrentBus;
    private EventBusConcurrent concurrentBus;

    @BeforeEach
    void beforeEach(){
        nonConcurrentBus = new EventBus();
        concurrentBus = new EventBusConcurrent();

        for(int i = 0; i < 10; i++){
            final var sleepySub = new SleepySubsriber();
            nonConcurrentBus.registerSubscriber(sleepySub);
            concurrentBus.registerSubscriber(sleepySub);
        }
    }

    @Test
    void timingsAreAsExpectedForConcurrentBlocking() throws ExecutionException, InterruptedException {
        final var initialTime = LocalDateTime.now().getNano();
        concurrentBus.publishEventBlocking(new AlarmClockEvent());
        final var finishTime = LocalDateTime.now().getNano();
        final var processTime = finishTime - initialTime;
        System.out.println(String.format("Process time for test [CONCURRENT BLOCKING]: %d", processTime));
    }

    @Test
    void timingsAreAsExpectedForConcurrentNonBlocking() throws ExecutionException, InterruptedException {
        final var initialTime = LocalDateTime.now().getNano();
        concurrentBus.publishEventNonBlocking(new AlarmClockEvent());
        final var finishTime = LocalDateTime.now().getNano();
        final var processTime = finishTime - initialTime;
        System.out.println(String.format("Process time for test [CONCURRENT NONBLOCKING]: %d", processTime));
    }

    @Test
    void timingsAreAsExpectedForLinear() throws ExecutionException, InterruptedException {
        final var initialTime = LocalDateTime.now().getNano();
        nonConcurrentBus.publishEvent(new AlarmClockEvent());
        final var finishTime = LocalDateTime.now().getNano();
        final var processTime = finishTime - initialTime;
        System.out.println(String.format("Process time for test [LINEAR]: %d", processTime));
    }

}
