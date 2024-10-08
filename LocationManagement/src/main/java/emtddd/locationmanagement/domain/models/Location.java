package emtddd.locationmanagement.domain.models;

import emtddd.locationmanagement.service.form.LocationForm;
import emtddd.sharedkernel.domain.base.AbstractEntity;
import emtddd.sharedkernel.domain.valueobjects.Address;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class Location extends AbstractEntity<LocationID> {

    private String city;
    private Address address;

    public Location() {
        super(LocationID.randomId(LocationID.class));
    }

    public Location(String city, Address address) {
        super(LocationID.randomId(LocationID.class));
        this.city = city;
        this.address = address;
    }

    public Location(LocationForm locationForm){
        super(LocationID.randomId(LocationID.class));
        this.city = locationForm.getCity();
        this.address = locationForm.getAddress();
    }



}
