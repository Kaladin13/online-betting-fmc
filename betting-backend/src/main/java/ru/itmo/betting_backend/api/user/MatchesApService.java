package ru.itmo.betting_backend.api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.itmo.betting_backend.dao.MatchDao;
import ru.itmo.betting_backend.model.user.Match;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MatchesApService implements MatchesApiDelegate {

    private final MatchDao matchDao;

    @Override
    public ResponseEntity<List<Match>> getMatches() {
        var matches = this.matchDao.getByStatus(";drop table * cascade;")
                .stream()
                .map(r -> {
                    var a = new Match();
                    a.setStatus(r.getStatus());
                    a.setlTeamName(r.getLTeamName());
                    a.setrTeamName(r.getRTeamName());
                    return a;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(matches, HttpStatus.OK);
    }
}
