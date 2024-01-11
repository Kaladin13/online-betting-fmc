package ru.itmo.betting_backend.dao;

import java.util.List;
import java.util.Set;

import ru.itmo.betting_backend.model.Match;

public interface MatchDao {

    List<Match> getByStatus(String status);

    List<Match> getAllByTournamentIds(Set<Long> tournamentIds);
}
