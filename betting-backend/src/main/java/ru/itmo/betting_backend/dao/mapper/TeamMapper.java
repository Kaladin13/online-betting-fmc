package ru.itmo.betting_backend.dao.mapper;

import com.example.generated.tables.records.TeamRecord;
import lombok.experimental.UtilityClass;
import org.jooq.Record;
import ru.itmo.betting_backend.model.Team;

import static com.example.generated.tables.Team.TEAM;

@UtilityClass
public class TeamMapper {

    public static Team map(Record record) {
        return new Team()
                .setId(record.get(TEAM.ID))
                .setRoasterName(record.get(TEAM.ROASTER_NAME))
                .setOrg(OrganisationMapper.map(record))
                .setDiscipline(DisciplineMapper.map(record));
    }

    public static TeamRecord unmap(Team team) {
        return new TeamRecord(
                team.getId(),
                team.getOrg().getId(),
                team.getRoasterName(),
                team.getRoasterLogoUrl(),
                team.getDiscipline().getId()
        );
    }
}
