package ru.itmo.betting_backend.model;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

@Data
@Accessors(chain = true)
public class Tournament {

    private Long id;

    private String name;

    private String logoUrl;

    // TODO delete
    private Discipline discipline;

    @Nullable
    private OffsetDateTime startedAt;

    @Nullable
    private OffsetDateTime endedAt;

    // TODO add matches
    private List<Match> matches;
}
