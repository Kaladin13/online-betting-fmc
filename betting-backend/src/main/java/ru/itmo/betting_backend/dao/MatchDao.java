package ru.itmo.betting_backend.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import ru.itmo.betting_backend.model.Match;

public interface MatchDao {

    List<Match> getAll();

    Optional<Match> getById(Long id);

    /**
     *
     * @return map of tournament id -> match array (without inner tournament)
     */
    Map<Long, List<Match>> getAllByTournamentIds(Set<Long> tournamentIds);
}
