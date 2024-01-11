package ru.itmo.betting_backend.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.betting_backend.dao.BidDao;
import ru.itmo.betting_backend.dao.mapper.BidMapper;
import ru.itmo.betting_backend.model.Bid;

import static com.example.generated.tables.Bids.BIDS;

@RequiredArgsConstructor
@Repository
public class BidDaoImpl implements BidDao {

    private final DSLContext dslContext;

    @Override
    @Transactional(readOnly = true)
    public Map<Long, List<Bid>> getAllBidsByEventId(Set<Long> eventsIds) {
        return dslContext.selectFrom(BIDS)
                .where(BIDS.ID.in(eventsIds))
                .fetch()
                .map(BidMapper::map)
                .stream()
                .collect(Collectors.toMap(
                        Bid::getBidEventId,
                        bid -> {
                            List<Bid> bids = new ArrayList<>();
                            bids.add(bid);
                            return bids;
                        },
                        (f, s) -> {
                            f.addAll(s);
                            return f;
                        }
                ));
    }
}
