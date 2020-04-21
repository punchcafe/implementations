package dev.punchcafe.implementations.adaptor.adaptors;

import java.time.LocalDateTime;

import dev.punchcafe.implementations.adaptor.PlayableEntity;
import dev.punchcafe.implementations.adaptor.rpc.EpisodeRpcEntity;
import dev.punchcafe.implementations.adaptor.rpc.MovieRpcEntity;

public class EpisodeAdaptor implements PlayableEntity {

    EpisodeRpcEntity entity;

    public EpisodeAdaptor(EpisodeRpcEntity entity){
        this.entity = entity;
    }

    @Override
    public LocalDateTime getStartDate() {
        return entity.getStartDate();
    }

    @Override
    public LocalDateTime getEndDate() {
        return entity.getEndDate();
    }
}
