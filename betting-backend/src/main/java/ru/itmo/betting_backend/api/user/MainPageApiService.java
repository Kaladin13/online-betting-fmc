package ru.itmo.betting_backend.api.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.itmo.betting_backend.dao.DisciplineDao;
import ru.itmo.betting_backend.dao.MatchDao;
import ru.itmo.betting_backend.dao.TournamentDao;
import ru.itmo.betting_backend.dao.mapper.DisciplineMapper;
import ru.itmo.betting_backend.model.Discipline;
import ru.itmo.betting_backend.model.Match;
import ru.itmo.betting_backend.model.Tournament;
import ru.itmo.betting_backend.model.user.MainPage;

@Service
@RequiredArgsConstructor
public class MainPageApiService implements MainPageApiDelegate {

    private final DisciplineDao disciplineDao;

    private final TournamentDao tournamentDao;

    private final MatchDao matchDao;

    @Override
    public ResponseEntity<MainPage> getMainPage() {
        List<Discipline> disciplines = disciplineDao.getAll();

        Map<Long, List<Tournament>> tournamentsByDisciplineId = tournamentDao.getAllByDisciplineIds(
                disciplines.stream()
                        .map(Discipline::getId)
                        .collect(Collectors.toSet())
        );

        List<Tournament> tournaments = new ArrayList<>();

        for (var discipline : disciplines) {
            var tournamentsByOneDiscipline = Optional.ofNullable(tournamentsByDisciplineId.get(discipline.getId()))
                    .orElse(new ArrayList<>());
            discipline.setTournaments(tournaments);
            tournamentsByOneDiscipline.addAll(tournaments);
        }

        Map<Long, List<Match>> matchesByTournamentId = matchDao.getAllByTournamentIds(
                tournaments.stream()
                        .map(Tournament::getId)
                        .collect(Collectors.toSet())
        );

        for (var tournament : tournaments) {
            tournament.setMatches(matchesByTournamentId.get(tournament.getId()));
        }

        var mainPageModel = new MainPage()
                .disciplines(
                        disciplines.stream()
                                .map(DisciplineMapper::mapToApiModel)
                                .toList()
                );

        return new ResponseEntity<>(mainPageModel, HttpStatus.OK);
    }
}
