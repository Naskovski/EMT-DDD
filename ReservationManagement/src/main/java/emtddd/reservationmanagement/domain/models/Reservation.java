package emtddd.reservationmanagement.domain.models;

import emtddd.reservationmanagement.domain.valueobjects.Vehicle;
import emtddd.reservationmanagement.domain.valueobjects.VehicleID;
import emtddd.sharedkernel.domain.base.AbstractEntity;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;


@Entity
@Getter
public class Reservation extends AbstractEntity<ReservationID> {
    @AttributeOverride(name="id", column = @Column(name="client_id"))
    private DomainObjectId client_id;
    @AttributeOverride(name="id", column = @Column(name="employee_id"))
    private DomainObjectId employee_id;
    @AttributeOverride(name="id", column = @Column(name="vehicle_id"))
    private VehicleID vehicle_id;
    private LocalDateTime reservation_start;
    private LocalDateTime reservation_end;

    public Reservation(DomainObjectId client_id,
                       DomainObjectId employee_id,
                       VehicleID vehicle_id,
                       LocalDateTime reservation_start,
                       LocalDateTime reservation_end){
        super(ReservationID.randomId(ReservationID.class));
        this.client_id = client_id;
        this.employee_id = employee_id;
        this.vehicle_id = vehicle_id;
        this.reservation_start = reservation_start;
        this.reservation_end = reservation_end;
    }
    public Reservation(){
        super(ReservationID.randomId(ReservationID.class));
    }

    public Reservation(@NonNull ReservationID id,
                       DomainObjectId client_id,
                       DomainObjectId employee_id,
                       VehicleID vehicle_id,
                       LocalDateTime reservation_start,
                       LocalDateTime reservation_end) {
        super(id);
        this.client_id = client_id;
        this.employee_id = employee_id;
        this.vehicle_id = vehicle_id;
        this.reservation_start = reservation_start;
        this.reservation_end = reservation_end;
    }
}
