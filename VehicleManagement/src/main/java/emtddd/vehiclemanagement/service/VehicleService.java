package emtddd.vehiclemanagement.service;

import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.events.reservations.ReservationCancelledEvent;
import emtddd.sharedkernel.domain.events.reservations.ReservationCreatedEvent;
import emtddd.vehiclemanagement.domain.models.Status;
import emtddd.vehiclemanagement.domain.models.Vehicle;
import emtddd.vehiclemanagement.domain.models.VehicleID;
import emtddd.vehiclemanagement.service.form.VehicleForm;
import emtddd.vehiclemanagement.xport.dto.VehicleDto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface VehicleService {
    List<VehicleDto> findAll();
    Optional<Vehicle> findById(VehicleID vehicleID);
    Vehicle create(VehicleForm vehicleForm);
    Vehicle retire(VehicleID vehicleID);
    Vehicle returnVehicle(VehicleID vehicleID, DomainObjectId locationId);
    Vehicle rent(VehicleID vehicleID, LocalDate startDate, LocalDate endDate);
    List<LocalDate> getAvailableDays(Vehicle vehicle, java.time.LocalDate startDate, java.time.LocalDate endDate);
    List<VehicleDto> findAllAvailable();
    void updateVehicleStatus(Vehicle vehicle, Status status, LocalDate startDate, LocalDate endDate);
    void handleReservationCreated(ReservationCreatedEvent event);
    void handleReservationCancelled(ReservationCancelledEvent event);
}
