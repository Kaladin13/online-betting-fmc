package ru.itmo.betting_backend.dao.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.itmo.betting_backend.dao.MatchDao;
import ru.itmo.betting_backend.dao.mapper.MatchMapper;
import ru.itmo.betting_backend.model.Match;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.generated.tables.Match.MATCH;
import static com.example.generated.tables.Team.TEAM;

@RequiredArgsConstructor
@Repository
public class MatchDaoImpl implements MatchDao {

    private final DSLContext dslContext;

    @Override
    public Optional<List<Match>> getByStatus(String status) {
        var teamL = TEAM.as("team_l");
        var teamR = TEAM.as("team_r");

        var fetchArray = dslContext.select(
                        MATCH.STATUS,
                        teamL.ROASTER_NAME,
                        teamR.ROASTER_NAME
                )
                .from(MATCH)
                .join(teamL)
                .on(teamL.ID.eq(MATCH.L_TEAM_ID))
                .join(teamR)
                .on(teamR.ID.eq(MATCH.R_TEAM_ID))
                .fetchArray();

        return Optional.of(Arrays.stream(fetchArray).map(MatchMapper::map).collect(Collectors.toList()));
    }
}
