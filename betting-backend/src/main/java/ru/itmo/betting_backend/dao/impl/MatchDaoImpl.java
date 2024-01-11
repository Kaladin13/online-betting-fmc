package ru.itmo.betting_backend.dao.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.betting_backend.dao.MatchDao;
import ru.itmo.betting_backend.dao.mapper.MatchMapper;
import ru.itmo.betting_backend.model.Match;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.generated.tables.Match.MATCH;
import static com.example.generated.tables.Team.TEAM;

@RequiredArgsConstructor
@Repository
public class MatchDaoImpl implements MatchDao {

    private final DSLContext dslContext;

    @Override
    @Transactional(readOnly = true)
    public List<Match> getByStatus(String status) {
        var teamL = TEAM.as("team_l");
        var teamR = TEAM.as("team_r");

        return dslContext.select(
                        MATCH.ID,
                        MATCH.STATUS,
                        teamL.ROASTER_NAME,
                        teamR.ROASTER_NAME
                )
                .from(MATCH)
                .join(teamL)
                .on(teamL.ID.eq(MATCH.L_TEAM_ID))
                .join(teamR)
                .on(teamR.ID.eq(MATCH.R_TEAM_ID))
                .fetch()
                .map(MatchMapper::map)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public Map<Long, List<Match>> getAllByTournamentIds(Set<Long> tournamentIds) {
        var teamL = TEAM.as("team_l");
        var teamR = TEAM.as("team_r");
        return dslContext.select(
                        MATCH.ID,
                        MATCH.STATUS,
                        teamL.ROASTER_NAME,
                        teamR.ROASTER_NAME,
                        MATCH.TOURNAMENT_ID
                )
                .from(MATCH)
                .join(teamL)
                .on(teamL.ID.eq(MATCH.L_TEAM_ID))
                .join(teamR)
                .on(teamR.ID.eq(MATCH.R_TEAM_ID))
                .where(MATCH.TOURNAMENT_ID.in(tournamentIds))
                .fetch()
                .map(MatchMapper::map)
                .stream()
                .collect(Collectors.toMap(
                        m -> m.getTournament().getId(),
                        m -> {
                            List<Match> matches = new ArrayList<>();
                            matches.add(m);
                            return matches;
                        },
                        (f, s) -> {
                            f.addAll(s);
                            return f;
                        }
                ));
    }
}
