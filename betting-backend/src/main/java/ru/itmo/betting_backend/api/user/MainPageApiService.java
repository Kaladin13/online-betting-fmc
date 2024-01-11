package ru.itmo.betting_backend.api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.itmo.betting_backend.dao.DisciplineDao;
import ru.itmo.betting_backend.model.user.MainPage;

@Service
@RequiredArgsConstructor
public class MainPageApiService implements MainPageApiDelegate {

    private final DisciplineDao disciplineDao;

    @Override
    public ResponseEntity<MainPage> getMainPage() {
        return null;
    }
}
