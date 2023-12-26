package ru.itmo.betting_backend.dao.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.betting_backend.dao.OrganisationDao;
import ru.itmo.betting_backend.dao.mapper.OrganisationMapper;
import ru.itmo.betting_backend.model.Org;

import java.util.List;
import java.util.Objects;

import static com.example.generated.tables.Organisation.ORGANISATION;

@Repository
@RequiredArgsConstructor
public class OrganisationImpl implements OrganisationDao {

    private final DSLContext dslContext;

    @Override
    @Transactional(readOnly = true)
    public List<Org> getOrgs() {
        return dslContext.select()
                .from(ORGANISATION)
                .fetch()
                .map(OrganisationMapper::map)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }
}
