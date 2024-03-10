package ru.itmo.betting_backend.dao.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.itmo.betting_backend.dao.BalanceDao;

import static com.example.generated.tables.BalanceTickets.BALANCE_TICKETS;

@Repository
@RequiredArgsConstructor
public class BalanceDaoImpl implements BalanceDao {

    private final DSLContext dslContext;

    @Override
    public void createBalanceTicket(String type, Long userId) {
        String BALANCE_TICKET_STATUS = "pending";

        dslContext.insertInto(BALANCE_TICKETS,
                        BALANCE_TICKETS.STATUS, BALANCE_TICKETS.TYPE, BALANCE_TICKETS.USER_ID)
                .values(BALANCE_TICKET_STATUS, type, userId)
                .execute();
    }
}
