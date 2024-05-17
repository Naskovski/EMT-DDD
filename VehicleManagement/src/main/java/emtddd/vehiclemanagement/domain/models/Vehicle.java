package emtddd.vehiclemanagement.domain.models;

import emtddd.sharedkernel.domain.base.AbstractEntity;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.vehiclemanagement.service.form.VehicleForm;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.NonNull;

@Entity
public class Vehicle extends AbstractEntity<VehicleID> {

    private String model_name;
    private String gps_id;
    private Status STATUS;
    @AttributeOverride(name="id", column = @Column(name="location_id"))
    private DomainObjectId locationId;

    public Vehicle(@NonNull DomainObjectId id) {
        super((VehicleID) id);
        this.STATUS = Status.UNAVAILABLE;
        this.locationId = null;
    }

    public Vehicle() {
        super(VehicleID.randomId(VehicleID.class));
        this.STATUS = Status.UNAVAILABLE;
        this.locationId = null;
        this.model_name = "unknown";
        this.gps_id = null;
    }

    public Vehicle(VehicleForm vehicleForm) {
        super(new VehicleID(vehicleForm.getRegistrationPlate()));
        this.STATUS = vehicleForm.getSTATUS();
        this.locationId = vehicleForm.getLocationId();
        this.gps_id = vehicleForm.getGps_id();
        this.model_name = vehicleForm.getModel_name();
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

    //dali treba da stoi ovaj logika tuj
}
