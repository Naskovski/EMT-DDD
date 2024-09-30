package emtddd.vehiclemanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import emtddd.sharedkernel.domain.base.ValueObject;
import emtddd.sharedkernel.domain.valueobjects.Address;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Location implements ValueObject {

    private LocationID locationID;
    private String city;
    private Address address;

    @JsonCreator
    public Location(@JsonProperty("id") @NonNull LocationID id,
                    @JsonProperty("city") String city,
                    @JsonProperty("address") Address address)
    {
        this.locationID = id;
        this.city = city;
        this.address = address;
    }
}