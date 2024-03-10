package ru.itmo.betting_backend.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

@Data
@Accessors(chain = true)
public class User {

    private Long id;

    private String login;

    private String password;

    private Long referralUserId;

    private BigDecimal balance;

    private Boolean isTrusted;

    @Nullable
    private String name;

    @Nullable
    private String surname;

    private String email;

    private String passport;
}
