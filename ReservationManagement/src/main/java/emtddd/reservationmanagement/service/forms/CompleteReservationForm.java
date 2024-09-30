package emtddd.reservationmanagement.service.forms;

import emtddd.reservationmanagement.domain.models.ReservationID;
import emtddd.reservationmanagement.domain.valueobjects.LocationID;
import emtddd.sharedkernel.domain.base.UserID;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class CompleteReservationForm {

    @NotNull
    private ReservationID reservationId;

    @NotNull
    private UserID employeeId;

    @NotNull
    private LocationID locationId;

    private ZonedDateTime timestamp;

    public CompleteReservationForm(ReservationID reservationId,
                                   UserID employeeId,
                                   LocationID locationId) {
        this.reservationId = reservationId;
        this.employeeId = employeeId;
        this.locationId = locationId;
        this.timestamp = ZonedDateTime.now();
    }
}
