package emtddd.reservationmanagement.domain.models;

import emtddd.reservationmanagement.domain.valueobjects.LocationID;
import emtddd.reservationmanagement.domain.valueobjects.VehicleID;
import emtddd.sharedkernel.domain.base.AbstractEntity;
import emtddd.sharedkernel.domain.base.UserID;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.ExtensionMethod;

import java.time.ZonedDateTime;


@Entity
@Getter
public class Reservation extends AbstractEntity<ReservationID> {
    @Embedded
    @AttributeOverride(name="id", column = @Column(name="client_id"))
    private UserID clientId;
    @Setter
    @Embedded
    @AttributeOverride(name="id", column = @Column(name="employee_id"))
    private UserID employeeID;
    @Embedded
    @AttributeOverride(name="id", column = @Column(name="vehicle_id"))
    private VehicleID vehicleID;
    @Embedded
    @AttributeOverride(name="id", column = @Column(name="location_id"))
    private LocationID locationId;
    private ZonedDateTime reservationStart;
    private ZonedDateTime reservationEnd;
    @Setter
    private ReservationStatus reservationStatus;

    public Reservation(UserID clientId,
                       UserID EmployeeID,
                       VehicleID VehicleID,
                       LocationID locationId,
                       ZonedDateTime reservationStart,
                       ZonedDateTime reservationEnd){
        super(ReservationID.randomId(ReservationID.class));
        this.clientId = clientId;
        this.employeeID = EmployeeID;
        this.vehicleID = VehicleID;
        this.locationId = locationId;
        this.reservationStart = reservationStart;
        this.reservationEnd = reservationEnd;
        this.reservationStatus = ReservationStatus.RESERVED;
    }
    public Reservation(){
        super(ReservationID.randomId(ReservationID.class));
    }

    public Reservation(@NonNull ReservationID id,
                       UserID clientId,
                       UserID EmployeeID,
                       VehicleID VehicleID,
                       LocationID locationId,
                       ZonedDateTime reservationStart,
                       ZonedDateTime reservationEnd) {
        super(id);
        this.clientId = clientId;
        this.employeeID = EmployeeID;
        this.vehicleID = VehicleID;
        this.locationId = locationId;
        this.reservationStart = reservationStart;
        this.reservationEnd = reservationEnd;
        this.reservationStatus = ReservationStatus.RESERVED;
    }
}