package ru.itmo.betting_backend.api.user;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.itmo.betting_backend.dao.BidDao;
import ru.itmo.betting_backend.dao.BidEventDao;
import ru.itmo.betting_backend.dao.MatchDao;
import ru.itmo.betting_backend.model.Bid;
import ru.itmo.betting_backend.model.BidEvent;
import ru.itmo.betting_backend.model.Match;

@Component
@RequiredArgsConstructor
public class MatchPageApiService implements MatchPageApiDelegate {

    private final MatchDao matchDao;

    private final BidDao bidDao;

    private final BidEventDao bidEventDao;

    @Override
    public ResponseEntity<Void> getMatchPage(Long matchId) {
        Match match = matchDao.getById(matchId).orElseThrow(); // 404

        List<BidEvent> bidEvents = bidEventDao.getAllByMatchId(matchId);

        match.setBidEvents(bidEvents);

        Map<Long, List<Bid>> bidsByBidEventId = bidDao.getAllBidsByEventId(
                bidEvents.stream()
                        .map(BidEvent::getId)
                        .collect(Collectors.toSet())
        );

        for (var bidEvent : bidEvents) {
            bidEvent.setBids(bidsByBidEventId.get(bidEvent.getId()));
        }

        return MatchPageApiDelegate.super.getMatchPage(matchId);
    }
}
