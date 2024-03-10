package ru.itmo.betting_backend.dao.mapper;

import lombok.experimental.UtilityClass;
import org.jooq.Record;
import ru.itmo.betting_backend.model.Org;
import ru.itmo.betting_backend.model.user.Organisation;

import java.math.BigDecimal;

import static com.example.generated.tables.Organisation.ORGANISATION;

@UtilityClass
public class OrganisationMapper {
    public static Org map(Record record) {
        return new Org()
                .setId(record.get(ORGANISATION.ID))
                .setName(record.get(ORGANISATION.NAME))
                .setLogoUrl(record.get(ORGANISATION.LOGO_URL))
                .setRegion(record.get(ORGANISATION.REGION));
    }

    public static Organisation mapToApiModel(Org org) {
        var a = new ru.itmo.betting_backend.model.user.Organisation();
        a.setId(BigDecimal.valueOf(org.getId()));
        a.setName(org.getName());
        a.setRegion(org.getRegion());
        a.setLogoUrl(org.getLogoUrl());

        return a;
    }
}
