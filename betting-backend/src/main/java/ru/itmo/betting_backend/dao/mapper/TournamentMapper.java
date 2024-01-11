package ru.itmo.betting_backend.dao.mapper;

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
                .setDiscipline(record.get(DISCIPLINE.ID) != null ?
                        DisciplineMapper.map(record) : new Discipline().setId(record.get(TOURNAMENT.DISCIPLINE_ID)))
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
}
