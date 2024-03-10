package ru.itmo.betting_backend.model;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

@Data
@Accessors(chain = true)
public class BidEvent {

    private Long id;

    private String name;

    private String status;

    private OffsetDateTime editedAt;

    private OffsetDateTime createdAt;

    @Nullable
    private List<Bid> bids;
}
