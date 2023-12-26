package ru.itmo.betting_backend.dao.mapper;

import lombok.experimental.UtilityClass;
import org.jooq.Record;
import ru.itmo.betting_backend.model.Match;
import ru.itmo.betting_backend.model.Tournament;

import static com.example.generated.tables.Match.MATCH;
import static com.example.generated.tables.Team.TEAM;

@UtilityClass
public class MatchMapper {

    public static Match map(Record record) {
        return new Match()
                .setId(record.get(MATCH.ID))
                .setStatus(record.get(MATCH.STATUS))
                .setLTeam(record.get(TEAM.as("team_l").ROASTER_NAME))
                .setRTeam(record.get(TEAM.as("team_r").ROASTER_NAME))
                .setTournament(new Tournament().setId(record.get(MATCH.TOURNAMENT_ID)));
    }

    public static ru.itmo.betting_backend.model.user.Match mapToApiModel(Match match) {
        var a = new ru.itmo.betting_backend.model.user.Match();
        a.setStatus(match.getStatus());
        a.setlTeamName(match.getLTeam());
        a.setrTeamName(match.getRTeam());
        a.setId(match.getId());
        return a;
    }
}
