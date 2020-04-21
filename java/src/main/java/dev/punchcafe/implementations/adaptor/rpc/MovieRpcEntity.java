package dev.punchcafe.implementations.adaptor.rpc;

import java.time.LocalDateTime;

public class MovieRpcEntity {
    private LocalDateTime startDate = LocalDateTime.now().plusDays(1);
    private LocalDateTime endDate = LocalDateTime.now().plusDays(1).plusHours(2);

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
