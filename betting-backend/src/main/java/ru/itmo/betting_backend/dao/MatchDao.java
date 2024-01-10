package ru.itmo.betting_backend.dao;

import ru.itmo.betting_backend.model.Match;

import java.util.List;
import java.util.Optional;

public interface MatchDao {

    Optional<List<Match>> getByStatus(String status);
}
