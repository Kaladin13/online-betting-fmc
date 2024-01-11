package ru.itmo.betting_backend.dao.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.itmo.betting_backend.dao.BidEventDao;
import ru.itmo.betting_backend.model.BidEvent;

@Repository
@RequiredArgsConstructor
public class BidEventDaoImpl implements BidEventDao {

    private final DSLContext dslContext;

    @Override
    public List<BidEvent> getAllByMatchId(Long matchId) {
        return null;
    }
}
