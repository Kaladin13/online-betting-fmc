package ru.itmo.betting_backend.api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.itmo.betting_backend.dao.DisciplineDao;
import ru.itmo.betting_backend.model.user.PingResponse;

@Component
@RequiredArgsConstructor
public class BettingApiService implements PingApiDelegate {

    private final DisciplineDao disciplineDao;

    @Override
    public ResponseEntity<PingResponse> ping() {
        return new ResponseEntity<>(
                new PingResponse()
                        .pong("pong"),
                HttpStatus.OK);
    }
}
