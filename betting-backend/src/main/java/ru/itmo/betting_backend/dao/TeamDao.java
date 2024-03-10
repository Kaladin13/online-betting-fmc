package ru.itmo.betting_backend.dao;

import java.util.Collection;
import java.util.List;

import com.example.generated.tables.records.TeamRecord;
import org.jooq.TableField;
import ru.itmo.betting_backend.model.Team;

public interface TeamDao {

    <T> List<Team> getTeamsBy(T value, TableField<TeamRecord, T> field);

    void addAll(Collection<Team> teams);
}
