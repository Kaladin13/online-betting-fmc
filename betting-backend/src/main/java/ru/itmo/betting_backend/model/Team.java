package ru.itmo.betting_backend.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

@Data
@Accessors(chain = true)
public class Team {

    private Long id;

    @Nullable
    private String roasterName;

    @Nullable
    private String roasterLogoUrl;

    private Discipline discipline;

    private Org org;
}
