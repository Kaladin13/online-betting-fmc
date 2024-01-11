package ru.itmo.betting_backend.dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import ru.itmo.betting_backend.model.Tournament;

public interface TournamentDao {

    Optional<Tournament> getById(Long id);

    void addAll(Collection<Tournament> tournaments);
}
