package ru.itmo.betting_backend.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.itmo.betting_backend.dao.TournamentDao;
import ru.itmo.betting_backend.dao.mapper.TournamentMapper;
import ru.itmo.betting_backend.model.Tournament;

import static com.example.generated.tables.Discipline.DISCIPLINE;
import static com.example.generated.tables.Tournament.TOURNAMENT;

@RequiredArgsConstructor
@Repository
public class TournamentDaoImpl implements TournamentDao {

    private final DSLContext dslContext;

    @Override
    public Optional<Tournament> getById(Long id) {
        return dslContext.select(
                        TOURNAMENT.ID,
                        TOURNAMENT.NAME,
                        TOURNAMENT.LOGO_URL,
                        TOURNAMENT.STARTED_AT,
                        TOURNAMENT.ENDED_AT,
                        DISCIPLINE.ID,
                        DISCIPLINE.NAME,
                        DISCIPLINE.LOGO_URL,
                        DISCIPLINE.IS_CYBERSPORT
                )
                .from(TOURNAMENT)
                .join(DISCIPLINE)
                .on(DISCIPLINE.ID.eq(TOURNAMENT.DISCIPLINE_ID))
                .fetchOptional()
                .map(TournamentMapper::map);
    }

    @Override
    public void addAll(Collection<Tournament> tournaments) {
        dslContext.batchInsert(
                tournaments.stream()
                        .map(TournamentMapper::unmap)
                        .toList()
        ).execute();
    }

    @Override
    public Map<Long, List<Tournament>> getAllByDisciplineIds(Set<Long> disciplineIds) {
        return dslContext.select(
                        TOURNAMENT.ID,
                        TOURNAMENT.NAME,
                        TOURNAMENT.LOGO_URL,
                        TOURNAMENT.STARTED_AT,
                        TOURNAMENT.ENDED_AT,
                        TOURNAMENT.DISCIPLINE_ID
                )
                .from(TOURNAMENT)
                .where(TOURNAMENT.DISCIPLINE_ID.in(disciplineIds))
                .fetch()
                .map(TournamentMapper::map)
                .stream()
                .collect(Collectors.toMap(
                        t -> t.getDiscipline().getId(),
                        t -> {
                            List<Tournament> list = new ArrayList<>();
                            list.add(t);
                            return list;
                        },
                        (f, s) -> {
                            f.addAll(s);
                            return f;
                        }
                ));
    }
}
