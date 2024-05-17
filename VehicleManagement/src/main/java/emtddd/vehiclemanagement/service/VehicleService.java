package emtddd.vehiclemanagement.service;

import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.vehiclemanagement.domain.models.Vehicle;
import emtddd.vehiclemanagement.domain.models.VehicleID;
import emtddd.vehiclemanagement.service.form.VehicleForm;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    List<Vehicle> findAll();
    Optional<Vehicle> findById(VehicleID vehicleID);
    Vehicle create(VehicleForm vehicleForm);
    Vehicle retire(VehicleID vehicleID);
    Vehicle returnVehicle(VehicleID vehicleID, DomainObjectId locationId);
    Vehicle rent(VehicleID vehicleID);
}
