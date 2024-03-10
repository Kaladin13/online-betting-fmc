package ru.itmo.betting_backend.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

@Data
@Accessors(chain = true)
public class Org {

    private Long id;

    private String name;

    @Nullable
    private String logoUrl;

    @Nullable
    private String region;
}
