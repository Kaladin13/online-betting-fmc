package ru.itmo.betting_backend.dao.impl;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import ru.itmo.betting_backend.dao.DisciplineDao;
import ru.itmo.betting_backend.dao.mapper.DisciplineMapper;
import ru.itmo.betting_backend.model.Discipline;

import static com.example.generated.tables.Discipline.DISCIPLINE;

@RequiredArgsConstructor
public class DisciplineDaoImpl implements DisciplineDao {

    private final DSLContext dslContext;

    @Override
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
}
