package emtddd.vehiclemanagement.domain.models;

import emtddd.sharedkernel.domain.base.DomainObjectId;
import lombok.NonNull;

public class VehicleID extends DomainObjectId {
    public VehicleID(@NonNull String id) {
        super(id);
    }

    private VehicleID(){
        super(VehicleID.randomId(VehicleID.class).getId());
    }

}
