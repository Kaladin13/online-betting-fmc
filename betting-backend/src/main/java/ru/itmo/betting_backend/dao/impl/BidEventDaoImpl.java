package ru.itmo.betting_backend.dao.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.betting_backend.dao.BidEventDao;
import ru.itmo.betting_backend.dao.mapper.BidEventMapper;
import ru.itmo.betting_backend.model.BidEvent;

import static com.example.generated.tables.BidEvent.BID_EVENT;

@Repository
@RequiredArgsConstructor
public class BidEventDaoImpl implements BidEventDao {

    private final DSLContext dslContext;

    @Override
    @Transactional(readOnly = true)
    public List<BidEvent> getAllByMatchId(Long matchId) {
        return dslContext.select(
                BID_EVENT.ID,
                BID_EVENT.NAME,
                BID_EVENT.STATUS,
                BID_EVENT.EDITED_AT,
                BID_EVENT.CREATED_AT
        )
                .from(BID_EVENT)
                .where(BID_EVENT.MATCH_ID.eq(matchId))
                .fetch()
                .map(BidEventMapper::map);
    }
}
