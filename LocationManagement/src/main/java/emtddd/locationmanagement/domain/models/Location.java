package emtddd.locationmanagement.domain.models;

import emtddd.locationmanagement.domain.valueobjects.Address;
import emtddd.sharedkernel.domain.base.AbstractEntity;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import jakarta.persistence.Entity;
import lombok.NonNull;


public class Location extends AbstractEntity<LocationID> {

    private String City;
    private Address address;

    private Location() {
        super(LocationID.randomId(LocationID.class));
    }

    public Location(String city, Address address) {
        super(LocationID.randomId(LocationID.class));
        City = city;
        this.address = address;
    }
}
