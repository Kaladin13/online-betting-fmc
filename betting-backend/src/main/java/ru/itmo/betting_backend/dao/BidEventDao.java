package ru.itmo.betting_backend.dao;

import java.util.List;

import ru.itmo.betting_backend.model.BidEvent;

public interface BidEventDao {

    List<BidEvent> getAllByMatchId(Long matchId);
}
