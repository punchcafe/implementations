package dev.punchcafe.implementations.event;

import java.time.LocalDateTime;

public class Birthday implements Event {

    @Override
    public LocalDateTime getTimeStamp() {
        return LocalDateTime.now();
    }
}
