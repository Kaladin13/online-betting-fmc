package ru.itmo.betting_backend.api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.itmo.betting_backend.dao.BalanceDao;
import ru.itmo.betting_backend.model.user.BalanceTicket;

@Component
@RequiredArgsConstructor
public class BalanceApiService implements BalanceApiDelegate {

    private final BalanceDao balanceDao;

    @Override
    public ResponseEntity<String> createBalanceTicket(BalanceTicket balanceTicket) {
        balanceDao.createBalanceTicket(balanceTicket.getType(), balanceTicket.getUserId().longValue());

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
