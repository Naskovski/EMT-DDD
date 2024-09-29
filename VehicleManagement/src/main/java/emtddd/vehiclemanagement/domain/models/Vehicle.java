package emtddd.vehiclemanagement.domain.models;

import emtddd.sharedkernel.domain.base.AbstractEntity;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.valueobjects.Money;
import emtddd.vehiclemanagement.service.form.VehicleForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Entity
@Getter
public class Vehicle extends AbstractEntity<VehicleID> {

    private String modelName;
    private String gpsId;
    private String registrationPlate;
    @AttributeOverride(name="id", column = @Column(name="location_id"))
    private DomainObjectId locationId;
    private Money pricePerDay;
    private Boolean isRetired;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Map<LocalDate, StatusOnDate> statusOnDate;

    private static final Pattern REGISTRATION_PATTERN = Pattern.compile("^[A-Z]{2}\\d{4}[A-Z]{2}$");

    public Vehicle() {
        super(VehicleID.randomId(VehicleID.class));
        this.statusOnDate = new HashMap<>();
        this.locationId = null;
        this.modelName = "unknown";
        this.gpsId = null;
        this.registrationPlate = null;
        this.pricePerDay = new Money(0.0);
        this.isRetired = false;
    }

    public Vehicle(VehicleForm vehicleForm) {
        super(VehicleID.randomId(VehicleID.class));
        this.statusOnDate = new HashMap<>();
        this.locationId = vehicleForm.getLocationId();
        this.gpsId = vehicleForm.getGpsId();
        this.modelName = vehicleForm.getModelName();
        this.pricePerDay = vehicleForm.getPricePerDay();
        this.isRetired = false;

        if (!REGISTRATION_PATTERN.matcher(vehicleForm.getRegistrationPlate()).matches()) {
            throw new IllegalArgumentException("Invalid vehicle ID format.");
        }
        this.registrationPlate = vehicleForm.getRegistrationPlate();
    }

    public boolean retire(){
        isRetired = true;
        return true;
    }

    public void return_vehicle(@NonNull DomainObjectId new_location_id){
        this.locationId = new_location_id;
    }
}
