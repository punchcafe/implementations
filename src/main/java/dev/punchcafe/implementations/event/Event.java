package dev.punchcafe.implementations.event;

import java.time.LocalDateTime;

public interface Event {
    LocalDateTime getTimeStamp();
    String message();
}
