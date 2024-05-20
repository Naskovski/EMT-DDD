package emtddd.employeemanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import emtddd.sharedkernel.domain.base.ValueObject;
import lombok.Getter;
import lombok.NonNull;


@Getter
public class Location implements ValueObject {

    private LocationID locationID;
    private String city;
    private Address address;

    @JsonCreator
    public Location(@JsonProperty("id") @NonNull LocationID id,
                   @JsonProperty("model_name") String city,
                   @JsonProperty("gps_id") Address address)
                   {
        this.locationID = id;
        this.city = city;
        this.address = address;
    }
}
