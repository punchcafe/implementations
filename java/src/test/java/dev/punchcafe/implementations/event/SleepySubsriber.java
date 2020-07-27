package dev.punchcafe.implementations.event;

public class SleepySubsriber implements Subscriber {
    @Override
    public void publishEvent(Event event) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex){
            throw new RuntimeException(ex.getMessage());
        }

    }
}
