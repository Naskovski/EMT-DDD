package emtddd.reservationmanagement.service.forms;

import emtddd.sharedkernel.domain.base.DomainObjectId;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationForm {

    @NotNull
    private DomainObjectId client_id;

    @NotNull
    private DomainObjectId employee_id;

    @NotNull
    private DomainObjectId vehicle_id;

    @NotNull
    @Future
    private LocalDateTime reservation_start;

    @NotNull
    @Future
    private LocalDateTime reservation_end;
}
