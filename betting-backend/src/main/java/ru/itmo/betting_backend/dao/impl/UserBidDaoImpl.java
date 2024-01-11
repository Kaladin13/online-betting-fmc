package ru.itmo.betting_backend.dao.impl;

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
//        dslContext.insertInto(USER_BIDS)
//                .set(USER_BIDS)
//                .set(USER_BIDS)
//                .set(USER_BIDS)
//                .set(USER_BIDS)
//                .set(USER_BIDS)

    }
}
