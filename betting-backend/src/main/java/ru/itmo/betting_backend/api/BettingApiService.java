package ru.itmo.betting_backend.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.itmo.generated.api.PingApiDelegate;
import ru.itmo.generated.model.PingResponse;

@Service
public class BettingApiService implements PingApiDelegate {
    @Override
    public ResponseEntity<PingResponse> ping() {
        return new ResponseEntity<>(
                new PingResponse()
                        .pong("pong"),
                HttpStatus.OK);
    }
}
