package emtddd.reservationmanagement.domain.models;

import emtddd.sharedkernel.domain.base.AbstractEntity;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;


@Getter
public class Reservation extends AbstractEntity<ReservationID> {
    private DomainObjectId client_id;
    private DomainObjectId employee_id;
    private DomainObjectId vehicle_id;
    private LocalDateTime reservation_datetime;

    public Reservation(){
        super(ReservationID.randomId(ReservationID.class));
    }

    public Reservation(@NonNull ReservationID id, DomainObjectId client_id, DomainObjectId employee_id, DomainObjectId vehicle_id, LocalDateTime reservation_datetime) {
        super(ReservationID.randomId(ReservationID.class));
        this.client_id = client_id;
        this.employee_id = employee_id;
        this.vehicle_id = vehicle_id;
        this.reservation_datetime = reservation_datetime;
    }
}
