package ru.itmo.betting_backend.dao.mapper;

import lombok.experimental.UtilityClass;
import org.jooq.Record;
import ru.itmo.betting_backend.model.Match;

import static com.example.generated.tables.Match.MATCH;
import static com.example.generated.tables.Team.TEAM;

@UtilityClass
public class MatchMapper {

    public static Match map(Record record) {
        return new Match()
                .setStatus(record.get(MATCH.STATUS))
                .setLTeamName(record.get(TEAM.as("team_l").ROASTER_NAME))
                .setRTeamName(record.get(TEAM.as("team_r").ROASTER_NAME));
    }
}
