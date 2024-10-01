package emtddd.reservationmanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import emtddd.sharedkernel.domain.base.ValueObject;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Vehicle implements ValueObject {

    private VehicleID vehicleId;
    private String modelName;
    private String gpsId;
    private String registrationPlate;
    private Location location;
    private Double pricePerDay;
    private Boolean isRetired;


    @JsonCreator
    public Vehicle(@JsonProperty("id") VehicleID vehicleId,
                   @JsonProperty("modelName") String modelName,
                   @JsonProperty("gpsId") String gpsId,
                   @JsonProperty("registrationPlate") String registrationPlate,
                   @JsonProperty("location") Location location,
                   @JsonProperty("pricePerDay") Double pricePerDay,
                   @JsonProperty("isRetired") Boolean isRetired) {
        this.vehicleId = vehicleId;
        this.modelName = modelName;
        this.gpsId = gpsId;
        this.registrationPlate = registrationPlate;
        this.location = location;
        this.pricePerDay = pricePerDay;
        this.isRetired = isRetired;
    }

}
