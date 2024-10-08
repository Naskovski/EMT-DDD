package emtddd.vehiclemanagement.domain.models;

import emtddd.sharedkernel.domain.base.DomainObjectId;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor
public class VehicleID extends DomainObjectId {
    public VehicleID(@NonNull String id) {
        super(id);
    }
}
