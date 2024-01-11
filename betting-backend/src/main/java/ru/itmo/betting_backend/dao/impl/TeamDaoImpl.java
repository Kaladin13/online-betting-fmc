package ru.itmo.betting_backend.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.example.generated.tables.records.TeamRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.TableField;
import org.springframework.stereotype.Repository;
import ru.itmo.betting_backend.dao.TeamDao;
import ru.itmo.betting_backend.dao.mapper.TeamMapper;
import ru.itmo.betting_backend.model.Team;

import static com.example.generated.tables.Discipline.DISCIPLINE;
import static com.example.generated.tables.Organisation.ORGANISATION;
import static com.example.generated.tables.Team.TEAM;

@Repository
@RequiredArgsConstructor
public class TeamDaoImpl implements TeamDao {

    private final DSLContext dslContext;

    @Override
    public <T> List<Team> getTeamsBy(T value, TableField<TeamRecord, T> field) {
        return dslContext.select(
                        TEAM.ID,
                        TEAM.ROASTER_NAME,
                        DISCIPLINE.ID,
                        DISCIPLINE.NAME,
                        DISCIPLINE.LOGO_URL,
                        DISCIPLINE.IS_CYBERSPORT,
                        ORGANISATION.ID,
                        ORGANISATION.NAME,
                        ORGANISATION.LOGO_URL,
                        ORGANISATION.REGION
                )
                .from(TEAM)
                .join(DISCIPLINE)
                .on(DISCIPLINE.ID.eq(TEAM.DISCIPLINE_ID))
                .join(ORGANISATION)
                .on(ORGANISATION.ID.eq(TEAM.ID))
                .fetch()
                .map(TeamMapper::map)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public void addAll(Collection<Team> teams) {
        dslContext.batchInsert(
                teams.stream()
                        .map(TeamMapper::unmap)
                        .toList()
        ).execute();
    }
}
