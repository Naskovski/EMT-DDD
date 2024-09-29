package emtddd.vehiclemanagement.xport.dto;

import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.vehiclemanagement.domain.models.Vehicle;
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
    private DomainObjectId locationId;
    private Double pricePerDay;
    private Boolean isRetired;


    public VehicleDto(Vehicle vehicle){
        this.modelName = vehicle.getModelName();
        this.gpsId = vehicle.getGpsId();
        this.registrationPlate = vehicle.getRegistrationPlate();
        this.isRetired = vehicle.getIsRetired();
        this.pricePerDay = vehicle.getPricePerDay().getValue();
        this.locationId = vehicle.getLocationId();
        this.vehicleId = vehicle.getId().getId();
    }
}
