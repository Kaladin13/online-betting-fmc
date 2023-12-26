package ru.itmo.betting_backend.dao.mapper;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import com.example.generated.tables.records.TournamentRecord;
import lombok.experimental.UtilityClass;
import org.jooq.Record;
import ru.itmo.betting_backend.model.Discipline;
import ru.itmo.betting_backend.model.Tournament;

import static com.example.generated.tables.Discipline.DISCIPLINE;
import static com.example.generated.tables.Tournament.TOURNAMENT;

@UtilityClass
public class TournamentMapper {

    public static Tournament map(Record record) {
        return new Tournament()
                .setId(record.get(TOURNAMENT.ID))
                .setName(record.get(TOURNAMENT.NAME))
                .setLogoUrl(record.get(TOURNAMENT.LOGO_URL))
                .setDiscipline(record.get(DISCIPLINE.NAME) != null ?
                        DisciplineMapper.map(record) : new Discipline().setId(record.get(TOURNAMENT.DISCIPLINE_ID)))
                .setStartedAt(record.get(TOURNAMENT.STARTED_AT))
                .setEndedAt(record.get(TOURNAMENT.ENDED_AT));
    }

    public static Tournament mapWithOutDiscipline(Record record) {
        return new Tournament()
                .setId(record.get(TOURNAMENT.ID))
                .setName(record.get(TOURNAMENT.NAME))
                .setLogoUrl(record.get(TOURNAMENT.LOGO_URL))
                .setDiscipline(new Discipline().setId(record.get(TOURNAMENT.DISCIPLINE_ID)))
                .setStartedAt(record.get(TOURNAMENT.STARTED_AT))
                .setEndedAt(record.get(TOURNAMENT.ENDED_AT));
    }

    public static TournamentRecord unmap(Tournament tournament) {
        return new TournamentRecord(
                tournament.getId(),
                tournament.getName(),
                tournament.getLogoUrl(),
                tournament.getDiscipline().getId(),
                tournament.getStartedAt(),
                tournament.getEndedAt()
        );
    }

    public static ru.itmo.betting_backend.model.user.Tournament mapToApi(Tournament tournament) {
        return new ru.itmo.betting_backend.model.user.Tournament()
                .id(tournament.getId())
                .name(tournament.getName())
                .startedAt(Optional.ofNullable(tournament.getStartedAt())
                        .map(Objects::toString)
                        .orElse(null)
                )
                .endedAt(Optional.ofNullable(tournament.getEndedAt())
                        .map(Objects::toString)
                        .orElse(null)
                )
                .matches(
                        Optional.ofNullable(tournament.getMatches())
                                .orElse(new ArrayList<>())
                                .stream()
                                .map(MatchMapper::mapToApiModel)
                                .toList()
                );
    }
}
