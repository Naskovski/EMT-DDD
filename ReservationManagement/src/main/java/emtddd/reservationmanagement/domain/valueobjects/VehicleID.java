package emtddd.reservationmanagement.domain.valueobjects;

import emtddd.sharedkernel.domain.base.DomainObjectId;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Embeddable
@NoArgsConstructor
public class VehicleID extends DomainObjectId {
    public VehicleID(@NonNull String id) {
        super(id);
    }


}
