package ru.itmo.betting_backend.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import ru.itmo.betting_backend.model.Bid;

public interface BidDao {

    Map<Long, List<Bid>> getAllBidsByEventId(Set<Long> eventsIds);
}
