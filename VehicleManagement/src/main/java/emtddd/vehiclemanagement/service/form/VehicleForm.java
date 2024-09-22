package emtddd.vehiclemanagement.service.form;

import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.valueobjects.Money;
import emtddd.vehiclemanagement.domain.models.Status;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleForm {
    @Nonnull
    private String registrationPlate;
    private String modelName;
    private String gpsId;
    private Status STATUS;
    @Nonnull
    private DomainObjectId locationId;
    private Money pricePerDay;
}
