package emtddd.vehiclemanagement.service.impl;

import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.events.reservations.ReservationCancelledEvent;
import emtddd.sharedkernel.domain.events.reservations.ReservationCreatedEvent;
import emtddd.vehiclemanagement.domain.exceptions.VehicleIdDoesNotExistException;
import emtddd.vehiclemanagement.domain.exceptions.VehicleNotAvailableException;
import emtddd.vehiclemanagement.domain.models.Status;
import emtddd.vehiclemanagement.domain.models.StatusOnDate;
import emtddd.vehiclemanagement.domain.models.Vehicle;
import emtddd.vehiclemanagement.domain.models.VehicleID;
import emtddd.vehiclemanagement.domain.repository.StatusOnDateRepository;
import emtddd.vehiclemanagement.domain.repository.VehicleRepository;
import emtddd.vehiclemanagement.service.VehicleService;
import emtddd.vehiclemanagement.service.form.VehicleForm;
import emtddd.vehiclemanagement.xport.dto.VehicleDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final StatusOnDateRepository statusOnDateRepository;

    @Override
    public List<VehicleDto> findAll() {
        return vehicleRepository.findAll().stream().map(VehicleDto::new).toList();
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
        vehicle.return_vehicle(locationId);
        return vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional
    public Vehicle rent(VehicleID vehicleID, LocalDate dateStart, LocalDate dateEnd) {
        Vehicle vehicle = this.findById(vehicleID).orElseThrow(VehicleIdDoesNotExistException::new);

        for (LocalDate date = dateStart; !date.isAfter(dateEnd); date = date.plusDays(1)) {
            Optional<StatusOnDate> existingStatus = statusOnDateRepository.findByVehicleIdAndDate(vehicle.getId(), date);
            if (existingStatus.isPresent() && existingStatus.get().getStatus().equals(Status.RENTED)) {
                throw new VehicleNotAvailableException();
            }
        }

        for (LocalDate date = dateStart; !date.isAfter(dateEnd); date = date.plusDays(1)) {
            StatusOnDate statusOnDate = new StatusOnDate();
            statusOnDate.setVehicle(vehicle);
            statusOnDate.setDate(date);
            statusOnDate.setStatus(Status.RENTED);
            statusOnDateRepository.save(statusOnDate);
        }

        return vehicle;
    }

    @Override
    public List<LocalDate> getAvailableDays(Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        List<LocalDate> availableDays = new ArrayList<>();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            Optional<StatusOnDate> statusOnDate = statusOnDateRepository.findByVehicleIdAndDate(vehicle.getId(), date);

            if (statusOnDate.isEmpty()  || statusOnDate.get().getStatus().equals(Status.AVAILABLE)) {
                availableDays.add(date);
            }
        }

        return availableDays;
    }

    @Override
    public List<VehicleDto> findAllAvailable() {
        return vehicleRepository.findAllByIsRetiredFalse().stream().map(VehicleDto::new).toList();
    }

    @Override
    public void updateVehicleStatus(Vehicle vehicle, Status status, LocalDate startDate, LocalDate endDate) {
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            Optional<StatusOnDate> statusOnDate = statusOnDateRepository.findByVehicleIdAndDate(vehicle.getId(), date);

            if (statusOnDate.isEmpty()) {
                statusOnDateRepository.save(new StatusOnDate(date, status, vehicle));
            }else {
                statusOnDate.get().setStatus(status);
                statusOnDateRepository.save(statusOnDate.get());
            }
        }
    }

    @Override
    public void handleReservationCreated(ReservationCreatedEvent event) {
        LocalDate startDate = event.getReservationStart().toLocalDate();
        LocalDate endDate = event.getReservationEnd().toLocalDate();

        Vehicle vehicle = vehicleRepository.findById(new VehicleID(event.getVehicleId().getId()))
                .orElseThrow(VehicleIdDoesNotExistException::new);

        updateVehicleStatus(vehicle, Status.RENTED, startDate, endDate);
    }

    @Override
    public void handleReservationCancelled(ReservationCancelledEvent event) {
        LocalDate startDate = event.getReservationStart().toLocalDate();
        LocalDate endDate = event.getReservationEnd().toLocalDate();

        Vehicle vehicle = vehicleRepository.findById(new VehicleID(event.getVehicleId().getId()))
                .orElseThrow(VehicleIdDoesNotExistException::new);

        updateVehicleStatus(vehicle, Status.AVAILABLE, startDate, endDate);
    }
}
