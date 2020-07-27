package dev.punchcafe.implementations.event;

import java.time.LocalDateTime;

public class AlarmClockEvent implements Event {
    @Override
    public LocalDateTime getTimeStamp() {
        return LocalDateTime.now();
    }

    @Override
    public String message() {
        return "Wake up!";
    }
}
