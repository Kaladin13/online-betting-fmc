package ru.itmo.betting_backend.dao.mapper;

import lombok.experimental.UtilityClass;
import org.jooq.Record;
import ru.itmo.betting_backend.model.Bid;

import static com.example.generated.tables.Bids.BIDS;

@UtilityClass
public class BidMapper {

    public static Bid map(Record record) {
        return new Bid()
                .setId(record.get(BIDS.ID))
                .setName(record.get(BIDS.NAME))
                .setStatus(record.get(BIDS.STATUS))
                .setRate(record.get(BIDS.RATE))
                .setBidEventId(record.get(BIDS.EVENT_ID));
    }
}
