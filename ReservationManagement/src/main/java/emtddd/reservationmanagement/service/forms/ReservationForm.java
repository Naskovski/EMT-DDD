package emtddd.reservationmanagement.service.forms;

import emtddd.reservationmanagement.domain.valueobjects.VehicleID;
import emtddd.sharedkernel.domain.base.UserID;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class ReservationForm {

    @NotNull
    private UserID clientId;

    private UserID employeeId;

    @NotNull
    private VehicleID vehicleId;

    @NotNull
    @Future
    private ZonedDateTime reservationStart;

    @NotNull
    @Future
    private ZonedDateTime reservationEnd;
}
