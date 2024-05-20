package emtddd.reservationmanagement.domain.valueobjects;

import emtddd.sharedkernel.domain.base.DomainObjectId;
import lombok.NonNull;

public class LocationID extends DomainObjectId {
    private LocationID(){
        super(LocationID.randomId(LocationID.class).getId());
    }

    public LocationID(@NonNull String id){
        super(id);
    }
}
