package ru.itmo.betting_backend.views;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmo.betting_backend.dao.BidDao;
import ru.itmo.betting_backend.dao.BidEventDao;
import ru.itmo.betting_backend.dao.MatchDao;
import ru.itmo.betting_backend.model.Bid;
import ru.itmo.betting_backend.model.BidEvent;
import ru.itmo.betting_backend.model.Match;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MatchPageView {

    private final MatchDao matchDao;

    private final BidDao bidDao;

    private final BidEventDao bidEventDao;

    @GetMapping("/match")
    public String showMatchPage(@RequestParam(name = "id", required = false) Long matchId, Model model) {
        Match match = matchDao.getById(matchId).orElse(null);

        if (match == null) {
            model.addAttribute("matchEntity", match);
            return "match";
        }

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

        model.addAttribute("matchEntity", match);
        return "match";
    }
}
