package ru.itmo.betting_backend.dao.mapper;

import lombok.experimental.UtilityClass;
import org.jooq.Record;
import ru.itmo.betting_backend.model.BidEvent;

import static com.example.generated.Tables.BID_EVENT;

@UtilityClass
public class BidEventMapper {

    public static BidEvent map(Record record) {
        return new BidEvent()
                .setId(record.get(BID_EVENT.ID))
                .setName(record.get(BID_EVENT.NAME))
                .setStatus(record.get(BID_EVENT.STATUS))
                .setEditedAt(record.get(BID_EVENT.EDITED_AT))
                .setCreatedAt(record.get(BID_EVENT.CREATED_AT));
    }
}
