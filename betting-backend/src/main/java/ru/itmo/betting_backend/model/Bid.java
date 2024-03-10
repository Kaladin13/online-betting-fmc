package ru.itmo.betting_backend.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Bid {

    private Long id;

    private Long bidEventId;

    private String name;

    private BigDecimal rate;

    private String status;
}
