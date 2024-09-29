package emtddd.vehiclemanagement.domain.repository;

import emtddd.vehiclemanagement.domain.models.StatusOnDate;
import emtddd.vehiclemanagement.domain.models.VehicleID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface StatusOnDateRepository extends JpaRepository<StatusOnDate, Long> {
    Optional<StatusOnDate> findByVehicleIdAndDate(VehicleID id, LocalDate date);
}
