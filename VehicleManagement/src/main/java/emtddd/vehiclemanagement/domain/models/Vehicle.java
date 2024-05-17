package emtddd.vehiclemanagement.domain.models;

import emtddd.sharedkernel.domain.base.AbstractEntity;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import lombok.NonNull;

public class Vehicle extends AbstractEntity<VehicleID> {

    private String model_name;
    private String gps_id;
    private Status STATUS;
    private DomainObjectId location_id;

    protected Vehicle(@NonNull DomainObjectId id) {
        super(VehicleID.randomId(VehicleID.class));
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
        this.location_id = new_location_id;
    }

    //dali treba da stoi ovaj logika tuj
}
