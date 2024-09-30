package emtddd.vehiclemanagement.xport.dto;

import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.vehiclemanagement.domain.models.Vehicle;
import emtddd.vehiclemanagement.domain.valueobjects.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class VehicleDto {
    private String vehicleId;
    private String modelName;
    private String gpsId;
    private String registrationPlate;
    private Location location;
    private Double pricePerDay;
    private Boolean isRetired;
}
