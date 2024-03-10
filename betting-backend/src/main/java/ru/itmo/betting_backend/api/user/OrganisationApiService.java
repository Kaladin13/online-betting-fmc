package ru.itmo.betting_backend.api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.itmo.betting_backend.dao.OrganisationDao;
import ru.itmo.betting_backend.dao.mapper.OrganisationMapper;
import ru.itmo.betting_backend.model.user.Organisation;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrganisationApiService implements OrgApiDelegate{

    private final OrganisationDao organisationDao;

    @Override
    public ResponseEntity<List<Organisation>> getOrganisations() {
        var orgs = this.organisationDao.getOrgs()
                .stream()
                .map(OrganisationMapper::mapToApiModel)
                .toList();

        return new ResponseEntity<>(orgs, HttpStatus.OK);
    }
}
