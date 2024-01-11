package ru.itmo.betting_backend.model;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

@Data
@Accessors(chain = true)
public class Match {

    private Long id;

    private String lTeam;

    private String rTeam;

    @Nullable
    private Integer bestOf;

    private Tournament tournament;

    @Nullable
    private OffsetDateTime startedAt;

    @Nullable
    private OffsetDateTime endedAt;

    @Nullable
    private String status;

    @Nullable
    private List<BidEvent> bidEvents;
}
