package ru.itmo.betting_backend.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserBid {

    private Long bidId;

    private Long userId;

    private BigDecimal amount;

    private OffsetDateTime editedAt;

    private OffsetDateTime createdAt;

    private BigDecimal fixedRate;

    private String status;
}
