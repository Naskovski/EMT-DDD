package emtddd.vehiclemanagement.service.form;

import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.vehiclemanagement.domain.models.Status;
import jakarta.annotation.Nonnull;
import lombok.Data;

@Data
public class VehicleForm {
    @Nonnull
    private String registrationPlate;
    private String model_name;
    private String gps_id;
    private Status STATUS;
    @Nonnull
    private DomainObjectId locationId;
}
