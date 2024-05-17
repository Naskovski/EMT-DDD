package emtddd.reservationmanagement.service.impl;

import emtddd.reservationmanagement.domain.exceptions.InvalidClientIdException;
import emtddd.reservationmanagement.domain.models.Reservation;
import emtddd.reservationmanagement.domain.models.ReservationID;
import emtddd.reservationmanagement.domain.repository.ClientRepository;
import emtddd.reservationmanagement.domain.repository.ReservationRepository;
import emtddd.reservationmanagement.service.ReservationService;
import emtddd.reservationmanagement.service.forms.ReservationForm;
import emtddd.sharedkernel.domain.base.UserID;
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
    private final ClientRepository clientRepository;
    private final Validator validator;

    @Override
    public ReservationID placeReservation(ReservationForm reservationForm) {
        Objects.requireNonNull(reservationForm,"reservation must not be null.");
        var constraintViolations = validator.validate(reservationForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }
        var newReservation = reservationRepository.saveAndFlush(toDomainObject(reservationForm));

        var client = clientRepository.findById((UserID) reservationForm.getClient_id()).orElseThrow(InvalidClientIdException::new);
        client.addPoints();
        clientRepository.save(client);

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

    private Reservation toDomainObject(ReservationForm reservationForm){
        return new Reservation(reservationForm.getClient_id(),
                                        reservationForm.getEmployee_id(),
                                        reservationForm.getVehicle_id(),
                                        reservationForm.getReservation_start(),
                                        reservationForm.getReservation_end());
    }
}
