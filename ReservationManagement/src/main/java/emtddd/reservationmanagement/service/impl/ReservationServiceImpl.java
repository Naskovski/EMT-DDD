package emtddd.reservationmanagement.service.impl;

import emtddd.reservationmanagement.domain.models.Reservation;
import emtddd.reservationmanagement.domain.models.ReservationID;
//import emtddd.reservationmanagement.domain.repository.ClientRepository;
import emtddd.reservationmanagement.domain.repository.ReservationRepository;
import emtddd.reservationmanagement.service.ReservationService;
import emtddd.reservationmanagement.service.forms.ReservationForm;
import emtddd.sharedkernel.domain.events.reservations.ReservationCreatedEvent;
import emtddd.sharedkernel.infra.DomainEventPublisher;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
//    private final ClientRepository clientRepository;
    private final Validator validator;
    private final DomainEventPublisher domainEventPublisher;

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


        ReservationCreatedEvent event = new ReservationCreatedEvent(
                newReservation.getId(),
                newReservation.getVehicle_id(),
                newReservation.getClient_id(),
                newReservation.getReservation_start(),
                newReservation.getReservation_end()
        );
        domainEventPublisher.publish(event);

        return newReservation.getId();
    }

    @Override
    public List<Reservation> listAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(ReservationID id) {
        return reservationRepository.findById(id);
    }

    private Reservation toDomainObject(ReservationForm reservationForm) {
        return new Reservation(reservationForm.getClientId(),
                reservationForm.getEmployeeId(),
                reservationForm.getVehicleId(),
                reservationForm.getReservationStart(),
                reservationForm.getReservationEnd());
    }
}
