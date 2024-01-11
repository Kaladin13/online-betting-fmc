package ru.itmo.betting_backend.model;


import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

@Data
@Accessors(chain = true)
public class Discipline {

    private Long id;

    private String name;

    @Nullable
    private String logoUrl;

    @Nullable
    private Boolean isCyberSport;

    @Nullable
    private List<Tournament> tournaments;
}
