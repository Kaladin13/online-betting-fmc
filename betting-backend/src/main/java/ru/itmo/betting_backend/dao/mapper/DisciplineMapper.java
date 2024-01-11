package ru.itmo.betting_backend.dao.mapper;

import java.util.ArrayList;
import java.util.Optional;

import lombok.experimental.UtilityClass;
import org.jooq.Record;
import ru.itmo.betting_backend.model.Discipline;

import static com.example.generated.tables.Discipline.DISCIPLINE;

@UtilityClass
public class DisciplineMapper {

    public static Discipline map(Record record) {
        return new Discipline()
                .setId(record.get(DISCIPLINE.ID))
                .setName(record.get(DISCIPLINE.NAME))
                .setLogoUrl(record.get(DISCIPLINE.LOGO_URL))
                .setIsCyberSport(record.get(DISCIPLINE.IS_CYBERSPORT));
    }

    public static ru.itmo.betting_backend.model.user.Discipline mapToApiModel(Discipline discipline) {
        ru.itmo.betting_backend.model.user.Discipline apiDiscipline =
                new ru.itmo.betting_backend.model.user.Discipline();

        apiDiscipline.setId(discipline.getId());
        apiDiscipline.setName(discipline.getName());
        apiDiscipline.setTournaments(
                Optional.ofNullable(discipline.getTournaments())
                        .orElse(new ArrayList<>())
                        .stream()
                        .map(TournamentMapper::mapToApi)
                        .toList()
        );
        return apiDiscipline;
    }
}
