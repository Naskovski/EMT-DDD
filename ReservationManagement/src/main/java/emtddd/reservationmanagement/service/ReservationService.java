package emtddd.reservationmanagement.service;

import emtddd.reservationmanagement.domain.models.Reservation;
import emtddd.reservationmanagement.domain.models.ReservationID;
import emtddd.reservationmanagement.domain.models.ReservationStatus;
import emtddd.reservationmanagement.domain.valueobjects.LocationID;
import emtddd.reservationmanagement.service.forms.ReservationForm;
import emtddd.reservationmanagement.xport.dto.ReservationDTO;
import emtddd.sharedkernel.domain.base.UserID;
import emtddd.sharedkernel.domain.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    ReservationID placeReservation(ReservationForm reservationForm);
    ReservationID cancelReservation(ReservationID reservationID);
    ReservationID startReservation(ReservationID reservationID, UserID employeeId);
    ReservationID completeReservation(ReservationID reservationID, LocationID locationID, UserID employeeId);
    List<Reservation> listAll();
    Page<ReservationDTO> findAllByClient(UserID clientId, Pageable pageable);
    Optional<Reservation> findById(ReservationID id);
    Page<ReservationDTO> findAllByStatusAndLocation(ReservationStatus status, LocationID locationId, Pageable pageable);
    Page<ReservationDTO> findAllByStatus(ReservationStatus status, Pageable pageable);
}
