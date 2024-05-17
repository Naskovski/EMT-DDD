package emtddd.locationmanagement.service;

import emtddd.locationmanagement.domain.models.Location;
import emtddd.locationmanagement.domain.models.LocationID;
import emtddd.locationmanagement.service.form.LocationForm;
import emtddd.sharedkernel.domain.base.DomainObjectId;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> findAll();
    Optional<Location> findById(LocationID locationID);
    Location create(LocationForm locationForm);
}
