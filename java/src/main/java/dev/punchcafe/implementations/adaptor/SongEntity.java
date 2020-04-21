package dev.punchcafe.implementations.adaptor;

import java.time.LocalDateTime;

public class SongEntity implements PlayableEntity {

    @Override
    public LocalDateTime getStartDate() {
        return LocalDateTime.now();
    }

    @Override
    public LocalDateTime getEndDate() {
        return LocalDateTime.now().plusMinutes(3);
    }
}
