package ru.itmo.betting_backend.api.user;

import org.springframework.http.ResponseEntity;
import ru.itmo.betting_backend.model.user.MainPage;

public class MatchPageApiService implements MatchPageApiDelegate {

    @Override
    public ResponseEntity<Void> getMatchPage(Long matchId) {
        return MatchPageApiDelegate.super.getMatchPage(matchId);
    }
}
