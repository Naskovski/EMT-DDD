package emtddd.vehiclemanagement.service.impl;

import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.vehiclemanagement.domain.exceptions.InvalidLocationIdException;
import emtddd.vehiclemanagement.domain.exceptions.VehicleIdDoesNotExistException;
import emtddd.vehiclemanagement.domain.exceptions.VehicleNotAvailableException;
import emtddd.vehiclemanagement.domain.models.Vehicle;
import emtddd.vehiclemanagement.domain.models.VehicleID;
import emtddd.vehiclemanagement.domain.repository.VehicleRepository;
import emtddd.vehiclemanagement.service.VehicleService;
import emtddd.vehiclemanagement.service.form.VehicleForm;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Optional<Vehicle> findById(VehicleID vehicleID) {
        return vehicleRepository.findById(vehicleID);
    }

    @Override
    public Vehicle create(VehicleForm vehicleForm) {
        return vehicleRepository.save(new Vehicle(vehicleForm));
    }

    @Override
    public Vehicle retire(VehicleID vehicleID) {
        Vehicle vehicle = findById(vehicleID).orElseThrow(VehicleIdDoesNotExistException::new);
        vehicle.retire();
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle returnVehicle(VehicleID vehicleID, DomainObjectId locationId) {
        Vehicle vehicle = findById(vehicleID).orElseThrow(VehicleIdDoesNotExistException::new);
        if(true) {//todo impl location exists
            vehicle.return_vehicle(locationId);
            return vehicleRepository.save(vehicle);
        }
        else throw new InvalidLocationIdException();
    }

    @Override
    public Vehicle rent(VehicleID vehicleID) {
        Vehicle vehicle = findById(vehicleID).orElseThrow(VehicleIdDoesNotExistException::new);
        if(vehicle.rent()) return vehicleRepository.save(vehicle);
        else throw new VehicleNotAvailableException();
    }
}
