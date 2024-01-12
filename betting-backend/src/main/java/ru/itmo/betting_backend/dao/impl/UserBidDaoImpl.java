package ru.itmo.betting_backend.dao.impl;

import java.time.OffsetDateTime;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.itmo.betting_backend.dao.UserBidDao;
import ru.itmo.betting_backend.model.UserBid;

import static com.example.generated.tables.UserBids.USER_BIDS;

@RequiredArgsConstructor
@Repository
public class UserBidDaoImpl implements UserBidDao {

    private final DSLContext dslContext;
    @Override
    public void persist(UserBid userBid) {
        dslContext.insertInto(USER_BIDS)
                .set(USER_BIDS.BID_ID, userBid.getBidId())
                .set(USER_BIDS.USER_ID, userBid.getUserId())
                .set(USER_BIDS.AMOUNT, userBid.getAmount())
                .set(USER_BIDS.CREATED_AT, OffsetDateTime.now())
                .set(USER_BIDS.FIXED_RATE, userBid.getFixedRate())
                .set(USER_BIDS.STATUS, userBid.getStatus())
                .execute();

    }
}
