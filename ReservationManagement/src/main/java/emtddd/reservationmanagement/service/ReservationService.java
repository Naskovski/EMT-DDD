package emtddd.reservationmanagement.service;

import emtddd.reservationmanagement.domain.models.Reservation;
import emtddd.reservationmanagement.domain.models.ReservationID;
import emtddd.reservationmanagement.service.forms.ReservationForm;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    ReservationID placeReservation(ReservationForm reservationForm);
    List<Reservation> listAll();
    Optional<Reservation> findById(ReservationID id);

}
