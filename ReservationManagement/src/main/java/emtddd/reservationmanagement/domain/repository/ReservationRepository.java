package emtddd.reservationmanagement.domain.repository;

import emtddd.reservationmanagement.domain.models.Reservation;
import emtddd.reservationmanagement.domain.models.ReservationID;
import emtddd.reservationmanagement.domain.models.ReservationStatus;
import emtddd.reservationmanagement.domain.valueobjects.LocationID;
import emtddd.sharedkernel.domain.base.UserID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, ReservationID> {
    Page<Reservation> findAllByClientIdOrderByReservationStart(UserID clientId, Pageable pageable);

    Page<Reservation> findAllByLocationIdAndReservationStatusOrderByReservationStart(LocationID locationId, ReservationStatus reservationStatus, Pageable pageable);

    Page<Reservation> findAllByReservationStatusOrderByReservationStart(ReservationStatus status, Pageable pageable);
}
