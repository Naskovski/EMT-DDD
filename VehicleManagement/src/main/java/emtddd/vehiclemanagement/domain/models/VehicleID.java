package emtddd.vehiclemanagement.domain.models;

import emtddd.sharedkernel.domain.base.DomainObjectId;
import lombok.NonNull;

import java.util.regex.Pattern;

public class VehicleID extends DomainObjectId {
    private static final Pattern REGISTRATION_PATTERN = Pattern.compile("^[A-Z]{2}\\d{4}[A-Z]{2}$");

    public VehicleID(@NonNull String id) {
        super(id);
        if (!REGISTRATION_PATTERN.matcher(id).matches()) {
            throw new IllegalArgumentException("Invalid vehicle ID format.");
        }
    }
}
