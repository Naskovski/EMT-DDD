package emtddd.vehiclemanagement.domain.models;

import emtddd.sharedkernel.domain.base.AbstractEntity;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.valueobjects.Money;
import emtddd.vehiclemanagement.service.form.VehicleForm;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NonNull;

import java.util.regex.Pattern;

@Entity
@Getter
public class Vehicle extends AbstractEntity<VehicleID> {

    private String modelName;
    private String gpsId;
    private Status STATUS;
    private String registrationPlate;
    @AttributeOverride(name="id", column = @Column(name="location_id"))
    private DomainObjectId locationId;
    private Money pricePerDay;

    private static final Pattern REGISTRATION_PATTERN = Pattern.compile("^[A-Z]{2}\\d{4}[A-Z]{2}$");


    public Vehicle() {
        super(VehicleID.randomId(VehicleID.class));
        this.STATUS = Status.UNAVAILABLE;
        this.locationId = null;
        this.modelName = "unknown";
        this.gpsId = null;
        this.registrationPlate = null;
        this.pricePerDay = new Money(0.0);
    }



    public Vehicle(VehicleForm vehicleForm) {
        super(VehicleID.randomId(VehicleID.class));
        this.STATUS = vehicleForm.getSTATUS();
        this.locationId = vehicleForm.getLocationId();
        this.gpsId = vehicleForm.getGpsId();
        this.modelName = vehicleForm.getModelName();
        this.pricePerDay = vehicleForm.getPricePerDay();

        if (!REGISTRATION_PATTERN.matcher(vehicleForm.getRegistrationPlate()).matches()) {
            throw new IllegalArgumentException("Invalid vehicle ID format.");
        }
        this.registrationPlate = vehicleForm.getRegistrationPlate();
    }

    public boolean rent(){
        STATUS = Status.RENTED;
        return true;
    }

    public boolean retire(){
        if(STATUS == Status.RENTED) return false;
        STATUS = Status.UNAVAILABLE;
        return true;
    }

    public void return_vehicle(@NonNull DomainObjectId new_location_id){
        STATUS = Status.AVAILABLE;
        this.locationId = new_location_id;
    }
}
