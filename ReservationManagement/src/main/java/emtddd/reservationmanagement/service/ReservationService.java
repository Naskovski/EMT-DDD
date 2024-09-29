package emtddd.reservationmanagement.service;

import emtddd.reservationmanagement.domain.models.Reservation;
import emtddd.reservationmanagement.domain.models.ReservationID;
import emtddd.reservationmanagement.domain.valueobjects.LocationID;
import emtddd.reservationmanagement.service.forms.ReservationForm;
import emtddd.sharedkernel.domain.base.UserID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    ReservationID placeReservation(ReservationForm reservationForm);
    ReservationID cancelReservation(ReservationID reservationID);
    List<Reservation> listAll();
    Page<Reservation> findAllByClient(UserID clientId, Pageable pageable);
    Optional<Reservation> findById(ReservationID id);
}
