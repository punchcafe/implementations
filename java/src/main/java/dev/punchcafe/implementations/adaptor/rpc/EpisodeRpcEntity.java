package dev.punchcafe.implementations.adaptor.rpc;

import java.time.LocalDateTime;

public class EpisodeRpcEntity {

    private LocalDateTime startDate = LocalDateTime.now().plusDays(1);
    private LocalDateTime endDate = LocalDateTime.now().plusDays(1).plusMinutes(30);

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
