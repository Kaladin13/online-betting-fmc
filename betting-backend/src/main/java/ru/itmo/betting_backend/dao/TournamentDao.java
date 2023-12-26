package ru.itmo.betting_backend.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import ru.itmo.betting_backend.model.Tournament;

public interface TournamentDao {

    Optional<Tournament> getById(Long id);

    void addAll(Collection<Tournament> tournaments);

    /**
     * without discipline mapping
     * @return Map disciplineID -> tournaments array
     */
    Map<Long, List<Tournament>> getAllByDisciplineIds(Set<Long> disciplineIds);
}
