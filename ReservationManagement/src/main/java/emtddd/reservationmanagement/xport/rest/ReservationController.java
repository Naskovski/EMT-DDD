package emtddd.reservationmanagement.xport.rest;

import emtddd.reservationmanagement.domain.models.Reservation;
import emtddd.reservationmanagement.domain.repository.ReservationRepository;
import emtddd.reservationmanagement.service.ReservationService;
import emtddd.reservationmanagement.service.forms.ReservationForm;
import lombok.AllArgsConstructor;
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
}

