package dev.punchcafe.implementations.adaptor;

import java.util.ArrayList;
import java.util.List;

import dev.punchcafe.implementations.adaptor.adaptors.EpisodeAdaptor;
import dev.punchcafe.implementations.adaptor.adaptors.MovieAdaptor;
import dev.punchcafe.implementations.adaptor.rpc.EpisodeRpcEntity;
import dev.punchcafe.implementations.adaptor.rpc.MovieRpcEntity;

public class BroadcastSchedule {

    List<PlayableEntity> scheduledEntities = new ArrayList<>();

    public void addToLineup(PlayableEntity entity) {
        this.scheduledEntities.add(entity);
    }

    public void printSchedule() {
        scheduledEntities.forEach(entity -> System.out.println(String.format("There will be a broadcast between %s and %s",
                                                                             entity.getStartDate(),
                                                                             entity.getEndDate())));
    }

    /**
     * This is a practice example of the adaptor pattern.
     *
     * Notes to self:
     * - Adaptor is really useful in applying inheritance/interface concepts
     * to external things which otherwise prohibit them. In this case we see how
     * adaptors allow us to use some RPC classes, which may not support inheritance,
     * as though they implemented a common interface.
     *
     * Anything which has a certain behaviour, but cannot be expressed polymorphically
     * because of some class constraint can use an adaptor to make it usable in the
     * desired use case.
     */
    public static void main(String[] args) {
        final var broadcastSchedule = new BroadcastSchedule();

        final var rpcMovie = new MovieRpcEntity();
        final var rpcEpisode = new EpisodeRpcEntity();

        final var nativeSongEntity = new SongEntity();

        broadcastSchedule.addToLineup(nativeSongEntity);
        broadcastSchedule.addToLineup(new MovieAdaptor(rpcMovie));
        broadcastSchedule.addToLineup(new EpisodeAdaptor(rpcEpisode));

        broadcastSchedule.printSchedule();
    }
}
