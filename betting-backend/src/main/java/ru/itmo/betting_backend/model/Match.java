package ru.itmo.betting_backend.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Match {
    private Long id;

    private String lTeamName;

    private String rTeamName;

    private String status;
}
