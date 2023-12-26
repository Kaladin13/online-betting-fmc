package ru.itmo.betting_backend.dao.impl;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.betting_backend.dao.DisciplineDao;
import ru.itmo.betting_backend.dao.mapper.DisciplineMapper;
import ru.itmo.betting_backend.model.Discipline;

import static com.example.generated.tables.Discipline.DISCIPLINE;

@RequiredArgsConstructor
@Repository
public class DisciplineDaoImpl implements DisciplineDao {

    private final DSLContext dslContext;

    @Override
    @Transactional(readOnly = true)
    public Optional<Discipline> getById(Long id) {
        return dslContext.select(
                        DISCIPLINE.ID,
                        DISCIPLINE.NAME,
                        DISCIPLINE.LOGO_URL,
                        DISCIPLINE.IS_CYBERSPORT
                )
                .from(DISCIPLINE)
                .where(DISCIPLINE.ID.eq(id))
                .fetchOptional()
                .map(DisciplineMapper::map);
    }

    @Override
    public List<Discipline> getAll() {
        return dslContext.select(
                        DISCIPLINE.ID,
                        DISCIPLINE.NAME,
                        DISCIPLINE.LOGO_URL,
                        DISCIPLINE.IS_CYBERSPORT
                )
                .from(DISCIPLINE)
                .fetch()
                .map(DisciplineMapper::map);
    }

    @Override
    public void persist(Discipline discipline) {
        dslContext.insertInto(DISCIPLINE)
                .set(DISCIPLINE.NAME, discipline.getName())
                .set(DISCIPLINE.LOGO_URL, discipline.getLogoUrl())
                .set(DISCIPLINE.IS_CYBERSPORT, discipline.getIsCyberSport())
                .execute();
    }
}
