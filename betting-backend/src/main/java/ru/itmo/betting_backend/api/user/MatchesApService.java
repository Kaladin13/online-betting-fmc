package ru.itmo.betting_backend.api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.itmo.betting_backend.dao.MatchDao;
import ru.itmo.betting_backend.dao.mapper.MatchMapper;
import ru.itmo.betting_backend.model.user.Match;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MatchesApService implements MatchesApiDelegate {

    private final MatchDao matchDao;

    @Override
    public ResponseEntity<List<Match>> getMatches() {
        var matches = this.matchDao.getByStatus(";drop table * cascade;")
                .stream()
                .map(MatchMapper::mapToApiModel)
                .toList();

        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addMatches(List<Match> match) {
        return MatchesApiDelegate.super.addMatches(match);
    }
}
