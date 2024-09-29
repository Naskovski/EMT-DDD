package emtddd.reservationmanagement.xport.rest;

import emtddd.reservationmanagement.domain.models.Reservation;
import emtddd.reservationmanagement.domain.models.ReservationID;
import emtddd.reservationmanagement.domain.repository.ReservationRepository;
import emtddd.reservationmanagement.domain.valueobjects.LocationID;
import emtddd.reservationmanagement.service.ReservationService;
import emtddd.reservationmanagement.service.forms.ReservationForm;
import emtddd.sharedkernel.domain.base.UserID;
import emtddd.sharedkernel.domain.exceptions.InvalidIdException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/create")
    public void createReservation (@RequestBody ReservationForm reservation) {
        reservationService.placeReservation(reservation);
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelReservation(@RequestParam String reservationId) {
        try {
            ReservationID reservationID = new ReservationID(reservationId);
            reservationService.cancelReservation(reservationID);
            return ResponseEntity.ok("Reservation canceled successfully");
        } catch (InvalidIdException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Reservation ID");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while canceling the reservation");
        }
    }

    @GetMapping("/filter/client/{clientId}")
    public ResponseEntity<Page<Reservation>> getReservationsByClient(
            @PathVariable String clientId,
            Pageable pageable) {
        UserID userID = new UserID(clientId);
        Page<Reservation> reservations = reservationService.findAllByClient(userID, pageable);
        return ResponseEntity.ok(reservations);
    }
}

