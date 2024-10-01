package emtddd.reservationmanagement.xport.dto;

import emtddd.reservationmanagement.domain.models.ReservationID;
import emtddd.reservationmanagement.domain.valueobjects.Location;
import emtddd.reservationmanagement.domain.valueobjects.UserDetails;
import emtddd.reservationmanagement.domain.valueobjects.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ReservationDTO {
    private ReservationID id;
    private UserDetails client;
    private UserDetails employee;
    private Vehicle vehicle;
    private Location location;
    private ZonedDateTime reservationStart;
    private ZonedDateTime reservationEnd;
    private String reservationStatus;
}

