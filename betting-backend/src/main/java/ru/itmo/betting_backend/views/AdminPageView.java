package ru.itmo.betting_backend.views;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmo.betting_backend.dao.DisciplineDao;
import ru.itmo.betting_backend.model.Discipline;

@Controller
@RequiredArgsConstructor
public class AdminPageView {

    private final DisciplineDao disciplineDao;

    @GetMapping("/admin")
    public String admin() {

        return "admin";
    }

    @PostMapping("/create-discipline")
    public String createDiscipline(@RequestParam(name = "name") String name,
                                   @RequestParam(name = "is_cybersport") Boolean isCyberSport) {
        Discipline discipline = new Discipline()
                .setName(name)
                .setIsCyberSport(isCyberSport);

        disciplineDao.persist(discipline);
        return "redirect:/admin";
    }
}
