package emtddd.reservationmanagement.service.impl;

import emtddd.reservationmanagement.domain.models.Reservation;
import emtddd.reservationmanagement.domain.models.ReservationID;
import emtddd.reservationmanagement.domain.models.ReservationStatus;
import emtddd.reservationmanagement.domain.repository.ReservationRepository;
import emtddd.reservationmanagement.domain.valueobjects.Location;
import emtddd.reservationmanagement.domain.valueobjects.LocationID;
import emtddd.reservationmanagement.domain.valueobjects.UserDetails;
import emtddd.reservationmanagement.domain.valueobjects.Vehicle;
import emtddd.reservationmanagement.service.ReservationService;
import emtddd.reservationmanagement.service.forms.ReservationForm;
import emtddd.reservationmanagement.xport.client.AppClientClient;
import emtddd.reservationmanagement.xport.client.EmployeeClient;
import emtddd.reservationmanagement.xport.client.LocationClient;
import emtddd.reservationmanagement.xport.client.VehicleClient;
import emtddd.reservationmanagement.xport.dto.ReservationDTO;
import emtddd.sharedkernel.domain.base.UserID;
import emtddd.sharedkernel.domain.events.reservations.ReservationCancelledEvent;
import emtddd.sharedkernel.domain.events.reservations.ReservationCompletedEvent;
import emtddd.sharedkernel.domain.events.reservations.ReservationCreatedEvent;
import emtddd.sharedkernel.domain.exceptions.InvalidIdException;
import emtddd.sharedkernel.infra.DomainEventPublisher;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final Validator validator;
    private final DomainEventPublisher domainEventPublisher;
    private final LocationClient locationClient;
    private final VehicleClient vehicleClient;
    private final AppClientClient appClientClient;
    private final EmployeeClient employeeClient;

    @Override
    public ReservationID placeReservation(ReservationForm reservationForm) {
        Objects.requireNonNull(reservationForm, "reservation must not be null.");
        var constraintViolations = validator.validate(reservationForm);
        if (!constraintViolations.isEmpty()) {
            constraintViolations.forEach(System.err::println);
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }
        var newReservation = reservationRepository.saveAndFlush(toDomainObject(reservationForm));

//        var client = clientRepository.findById(reservationForm.getClient_id()).orElseThrow(InvalidClientIdException::new);
//        client.addPoints();
//        clientRepository.save(client);
        //todo add this in usermanagement with kafka listener


        ReservationCreatedEvent event = new ReservationCreatedEvent(
                newReservation.getId(),
                newReservation.getVehicleID(),
                newReservation.getClientId(),
                newReservation.getLocationId(),
                newReservation.getReservationStart(),
                newReservation.getReservationEnd()
        );
        domainEventPublisher.publish(event);

        return newReservation.getId();
    }

    @Override
    public ReservationID cancelReservation(ReservationID reservationID) throws InvalidIdException {
        Reservation reservation = this.findById(reservationID).orElseThrow(InvalidIdException::new);
        reservation.setReservationStatus(ReservationStatus.CANCELED);
        ReservationCancelledEvent event = new ReservationCancelledEvent(
                reservation.getId(),
                reservation.getVehicleID(),
                reservation.getClientId(),
                reservation.getLocationId(),
                reservation.getReservationStart(),
                reservation.getReservationEnd()
        );
        domainEventPublisher.publish(event);

        return reservation.getId();
    }

    @Override
    public ReservationID startReservation(ReservationID reservationID, UserID employeeId) {
        Reservation reservation = this.findById(reservationID).orElseThrow(InvalidIdException::new);
        reservation.setReservationStatus(ReservationStatus.STARTED);
        reservation.setEmployeeID(employeeId);
        return reservation.getId();
    }

    @Override
    public ReservationID completeReservation(ReservationID reservationID, LocationID locationID, UserID employeeId) {
        Reservation reservation = this.findById(reservationID).orElseThrow(InvalidIdException::new);
        reservation.setReservationStatus(ReservationStatus.COMPLETED);
        reservation.setEmployeeID(employeeId);
        ReservationCompletedEvent event = new ReservationCompletedEvent(
                reservation.getId(),
                reservation.getVehicleID(),
                reservation.getClientId(),
                locationID,
                reservation.getReservationStart(),
                reservation.getReservationEnd()
        );
        domainEventPublisher.publish(event);

        return reservation.getId();
    }

    @Override
    public List<Reservation> listAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Page<ReservationDTO> findAllByClient(UserID clientId, Pageable pageable) {
        return reservationRepository.findAllByClientIdOrderByReservationStart(clientId, pageable).map(reservation -> {
            Vehicle vehicle = vehicleClient.findById(reservation.getVehicleID());
            Location location = locationClient.findById(reservation.getLocationId());
            UserDetails client = appClientClient.findById(reservation.getClientId());
            UserDetails employee = employeeClient.findById(reservation.getEmployeeID());

            return new ReservationDTO(
                    reservation.getId(),
                    client,
                    employee,
                    vehicle,
                    location,
                    reservation.getReservationStart(),
                    reservation.getReservationEnd(),
                    reservation.getReservationStatus().name()
            );
        });
    }


    @Override
    public Optional<Reservation> findById(ReservationID id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Page<ReservationDTO> findAllByStatusAndLocation(ReservationStatus status, LocationID locationId, Pageable pageable) {
        return reservationRepository.findAllByLocationIdAndReservationStatusOrderByReservationStart(locationId, status, pageable).map(reservation -> {
            Vehicle vehicle = vehicleClient.findById(reservation.getVehicleID());
            Location location = locationClient.findById(reservation.getLocationId());
            UserDetails client = appClientClient.findById(reservation.getClientId());
            UserDetails employee = employeeClient.findById(reservation.getEmployeeID());

            return new ReservationDTO(
                    reservation.getId(),
                    client,
                    employee,
                    vehicle,
                    location,
                    reservation.getReservationStart(),
                    reservation.getReservationEnd(),
                    reservation.getReservationStatus().name()
            );
        });
    }

    @Override
    public Page<ReservationDTO> findAllByStatus(ReservationStatus status, Pageable pageable) {
        return reservationRepository.findAllByReservationStatusOrderByReservationStart(status, pageable).map(reservation -> {
            Vehicle vehicle = vehicleClient.findById(reservation.getVehicleID());
            Location location = locationClient.findById(reservation.getLocationId());
            UserDetails client = appClientClient.findById(reservation.getClientId());
            UserDetails employee = employeeClient.findById(reservation.getEmployeeID());

            return new ReservationDTO(
                    reservation.getId(),
                    client,
                    employee,
                    vehicle,
                    location,
                    reservation.getReservationStart(),
                    reservation.getReservationEnd(),
                    reservation.getReservationStatus().name()
            );
        });
    }

    private Reservation toDomainObject(ReservationForm reservationForm) {
        return new Reservation(reservationForm.getClientId(),
                reservationForm.getEmployeeId(),
                reservationForm.getVehicleId(),
                reservationForm.getLocationId(),
                reservationForm.getReservationStart(),
                reservationForm.getReservationEnd());
    }
}
