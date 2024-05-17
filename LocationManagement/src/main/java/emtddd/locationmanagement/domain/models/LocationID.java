package emtddd.locationmanagement.domain.models;

import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.base.UserID;
import lombok.NonNull;

public class LocationID extends DomainObjectId {
    private LocationID(){
        super(LocationID.randomId(LocationID.class).getId());
    }

    public LocationID(@NonNull String id){
        super(id);
    }
}
