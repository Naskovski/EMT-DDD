package emtddd.vehiclemanagement.domain.repository;

import emtddd.vehiclemanagement.domain.models.Vehicle;
import emtddd.vehiclemanagement.domain.models.VehicleID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, VehicleID> {
    List<Vehicle> findAllByIsRetiredFalse();
}
