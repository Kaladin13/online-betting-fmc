package ru.itmo.betting_backend.dao.mapper;

import com.example.generated.tables.records.UserRecord;
import lombok.experimental.UtilityClass;
import org.jooq.Record;
import ru.itmo.betting_backend.model.User;

import static com.example.generated.tables.User.USER;

@UtilityClass
public class UserMapper {

    public static User map(Record record) {
        return new User()
                .setId(record.get(USER.ID))
                .setLogin(record.get(USER.LOGIN))
                .setPassword(record.get(USER.PASSWORD))
                .setReferralUserId(record.get(USER.REFERRAL_ID))
                .setBalance(record.get(USER.BALANCE))
                .setIsTrusted(record.get(USER.IS_TRUSTED))
                .setName(record.get(USER.NAME))
                .setSurname(record.get(USER.SURNAME))
                .setEmail(record.get(USER.EMAIL))
                .setPassport(record.get(USER.PASSPORT));
    }

    public static UserRecord unmap(User user) {
        return new UserRecord(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getReferralUserId(),
                user.getBalance(),
                user.getIsTrusted(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPassport()
        );
    }
}
