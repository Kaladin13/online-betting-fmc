package ru.itmo.betting_backend.dao;

import java.util.Optional;

import ru.itmo.betting_backend.model.Discipline;

public interface DisciplineDao {

    Optional<Discipline> getById(Long id);
}
