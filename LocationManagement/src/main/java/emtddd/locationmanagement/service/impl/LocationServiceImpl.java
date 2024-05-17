package emtddd.locationmanagement.service.impl;

import emtddd.locationmanagement.domain.models.Location;
import emtddd.locationmanagement.domain.models.LocationID;
import emtddd.locationmanagement.domain.repository.LocationRepository;
import emtddd.locationmanagement.service.LocationService;
import emtddd.locationmanagement.service.form.LocationForm;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Optional<Location> findById(LocationID locationID) {
        return locationRepository.findById(locationID);
    }

    @Override
    public Location create(LocationForm locationForm) {
        return locationRepository.save(new Location(locationForm));
    }
}
