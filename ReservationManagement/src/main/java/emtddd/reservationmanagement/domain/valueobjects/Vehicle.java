package emtddd.reservationmanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import emtddd.sharedkernel.domain.base.DomainObjectId;;
import emtddd.sharedkernel.domain.base.ValueObject;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NonNull;

import java.util.regex.Pattern;

@Getter
public class Vehicle implements ValueObject {

    private VehicleID vehicleID;
    private String model_name;
    private String gps_id;
    private Status STATUS;
    private String registration_plate;
    @AttributeOverride(name="id", column = @Column(name="location_id"))
    private DomainObjectId locationId;

    private static final Pattern REGISTRATION_PATTERN = Pattern.compile("^[A-Z]{2}\\d{4}[A-Z]{2}$");


    @JsonCreator
    public Vehicle(@JsonProperty("id") @NonNull VehicleID id,
                   @JsonProperty("model_name") String model_name,
                   @JsonProperty("gps_id") String gps_id,
                   @JsonProperty("status") Status STATUS,
                   @JsonProperty("registration_plate") String registration_plate,
                   @JsonProperty("locationId") DomainObjectId locationId) {
        this.vehicleID = id;
        this.model_name = model_name;
        this.gps_id = gps_id;
        this.STATUS = STATUS;
        this.registration_plate = registration_plate;
        this.locationId = locationId;
    }


    public boolean rent(){
        STATUS = Status.RENTED;
        return true;
    }

    public boolean retire(){
        if(STATUS == Status.RENTED) return false;
        STATUS = Status.UNAVAILABLE;
        return true;
    }

    public void return_vehicle(@NonNull DomainObjectId new_location_id){
        STATUS = Status.AVAILABLE;
        this.locationId = new_location_id;
    }
}
