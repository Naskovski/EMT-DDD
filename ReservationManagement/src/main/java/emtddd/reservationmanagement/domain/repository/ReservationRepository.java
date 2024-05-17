package emtddd.reservationmanagement.domain.repository;

import emtddd.reservationmanagement.domain.models.Reservation;
import emtddd.reservationmanagement.domain.models.ReservationID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, ReservationID> {
}
