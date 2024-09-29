package emtddd.sharedkernel.domain.events.reservations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class ReservationCancelledEvent extends DomainEvent {
    private final DomainObjectId reservationId;
    private final DomainObjectId vehicleId;
    private final DomainObjectId clientId;
    private final DomainObjectId locationId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private final ZonedDateTime reservationStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private final ZonedDateTime reservationEnd;

    @JsonCreator
    public ReservationCancelledEvent(
            @JsonProperty("reservationId") DomainObjectId reservationId,
            @JsonProperty("vehicleId") DomainObjectId vehicleId,
            @JsonProperty("clientId") DomainObjectId clientId,
            @JsonProperty("locationId") DomainObjectId locationId,
            @JsonProperty("reservationStart") ZonedDateTime reservationStart,
            @JsonProperty("reservationEnd") ZonedDateTime reservationEnd) {
        super("reservation-cancelled");
        this.reservationId = reservationId;
        this.vehicleId = vehicleId;
        this.clientId = clientId;
        this.locationId = locationId;
        this.reservationStart = reservationStart;
        this.reservationEnd = reservationEnd;
    }
}
