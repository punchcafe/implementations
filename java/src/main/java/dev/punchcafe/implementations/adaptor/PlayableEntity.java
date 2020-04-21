package dev.punchcafe.implementations.adaptor;

import java.time.LocalDateTime;

public interface PlayableEntity {
    LocalDateTime getStartDate();
    LocalDateTime getEndDate();
}
