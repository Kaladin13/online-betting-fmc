package ru.itmo.betting_backend.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.itmo.betting_backend.model.PingResponse;

@Component
public class BettingApiService implements PingApiDelegate {

    @Override
    public ResponseEntity<PingResponse> ping() {
        return new ResponseEntity<>(
                new PingResponse()
                        .pong("pong"),
                HttpStatus.OK);
    }
}
