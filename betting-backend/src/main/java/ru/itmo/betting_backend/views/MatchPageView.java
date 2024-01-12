package ru.itmo.betting_backend.views;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmo.betting_backend.dao.BidDao;
import ru.itmo.betting_backend.dao.BidEventDao;
import ru.itmo.betting_backend.dao.MatchDao;
import ru.itmo.betting_backend.dao.UserBidDao;
import ru.itmo.betting_backend.model.Bid;
import ru.itmo.betting_backend.model.BidEvent;
import ru.itmo.betting_backend.model.Match;
import ru.itmo.betting_backend.model.UserBid;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MatchPageView {

    private final MatchDao matchDao;

    private final BidDao bidDao;

    private final BidEventDao bidEventDao;

    private final UserBidDao userBidDao;

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

    @PostMapping("/create-user-bid")
    public String createUserBid(@RequestParam(name = "user_id") Long userId,
                                @RequestParam(name = "bid_id") Long bidId,
                                @RequestParam(name = "amount") BigDecimal amount,
                                Model model) {
        BigDecimal fixedRate = bidDao.getById(bidId).orElseThrow().getRate();

        UserBid userBid = new UserBid()
                .setUserId(userId)
                .setBidId(bidId)
                .setAmount(amount)
                .setFixedRate(fixedRate)
                .setStatus("pending");

        userBidDao.persist(userBid);

        return "redirect:/main-page";
    }
}
