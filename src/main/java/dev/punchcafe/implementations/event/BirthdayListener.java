package dev.punchcafe.implementations.event;

public class BirthdayListener implements Subscriber {

    @Override
    public void publishEvent(final Event event) {
        if(event instanceof Birthday){
            System.out.println("Happy Birthday");
        }
    }
}
