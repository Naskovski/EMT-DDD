package emtddd.sharedkernel.domain.events.reservations;

//import emtddd.reservationmanagement.domain.models.Reservation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.events.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
public class ReservationCreatedEvent extends DomainEvent {
    private final DomainObjectId reservationId;
    private final DomainObjectId vehicleId;
    private final DomainObjectId clientId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private final ZonedDateTime reservationStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private final ZonedDateTime reservationEnd;

    @JsonCreator
    public ReservationCreatedEvent(
            @JsonProperty("reservationId") DomainObjectId reservationId,
            @JsonProperty("vehicleId") DomainObjectId vehicleId,
            @JsonProperty("clientId") DomainObjectId clientId,
            @JsonProperty("reservationStart") ZonedDateTime reservationStart,
            @JsonProperty("reservationEnd") ZonedDateTime reservationEnd) {
        super("reservation-created");
        this.reservationId = reservationId;
        this.vehicleId = vehicleId;
        this.clientId = clientId;
        this.reservationStart = reservationStart;
        this.reservationEnd = reservationEnd;


        System.out.println("THIS IS INSIDE THE CONTRUCTR OF RESERVATION CREATED EVENT");

        System.out.printf("reservationId parameter: %s\n", reservationId);
        System.out.printf("reservationId of the event: %s\n", this.reservationId);
        System.out.printf("vehicleId parameter: %s\n", vehicleId);
        System.out.printf("vehicleId of the event: %s\n", vehicleId);
        System.out.printf("clientId parameter: %s\n", clientId);
        System.out.printf("reservationStart parameter: %s\n", reservationStart);
        System.out.printf("reservationEnd parameter: %s\n", reservationEnd);

        System.out.println("[============[END OF CONTRUCTOR]====================]");
    }
}
