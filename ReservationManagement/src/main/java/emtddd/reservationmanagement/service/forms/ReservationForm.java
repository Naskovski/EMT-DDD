package emtddd.reservationmanagement.service.forms;

import emtddd.reservationmanagement.domain.valueobjects.VehicleID;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReservationForm {

    @NotNull
    private DomainObjectId client_id;

    @NotNull
    private DomainObjectId employee_id;

    @NotNull
    private VehicleID vehicle_id;

    @NotNull
    @Future
    private LocalDateTime reservation_start;

    @NotNull
    @Future
    private LocalDateTime reservation_end;
}
