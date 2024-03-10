package ru.itmo.betting_backend.views;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itmo.betting_backend.dao.DisciplineDao;
import ru.itmo.betting_backend.dao.MatchDao;
import ru.itmo.betting_backend.dao.TournamentDao;
import ru.itmo.betting_backend.model.Discipline;
import ru.itmo.betting_backend.model.Match;
import ru.itmo.betting_backend.model.Tournament;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainPageView {

    private final DisciplineDao disciplineDao;

    private final TournamentDao tournamentDao;

    private final MatchDao matchDao;

    @GetMapping("/main-page")
    public String showMainPage(Model model) {
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
            discipline.setTournaments(tournamentsByOneDiscipline);
            tournaments.addAll(tournamentsByOneDiscipline);
        }

        Map<Long, List<Match>> matchesByTournamentId = matchDao.getAllByTournamentIds(
                tournaments.stream()
                        .map(Tournament::getId)
                        .collect(Collectors.toSet())
        );

        for (var tournament : tournaments) {
            tournament.setMatches(matchesByTournamentId.get(tournament.getId()));
        }

        disciplines = disciplines
                .stream().filter(r -> {
                    assert r.getTournaments() != null;
                    return r.getTournaments()
                            .stream().allMatch(p -> p.getMatches() != null);
                })
                .filter(r -> r.getTournaments().stream().allMatch(p -> {
                    assert p.getMatches() != null;
                    return p.getMatches().size() >= 3;
                })).filter(r -> !r.getTournaments().isEmpty()).toList();


        model.addAttribute("disciplines", disciplines);
        return "index";
    }
}
