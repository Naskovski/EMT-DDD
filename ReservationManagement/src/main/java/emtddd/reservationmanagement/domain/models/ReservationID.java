package emtddd.reservationmanagement.domain.models;

import emtddd.sharedkernel.domain.base.DomainObjectId;
import jakarta.persistence.Embeddable;
import lombok.NonNull;

public class ReservationID extends DomainObjectId {
    private ReservationID(){
        super(ReservationID.randomId(ReservationID.class).getId());
    }

    public ReservationID(@NonNull String id){
        super(id);
    }
}
