package emtddd.locationmanagement.service.form;

import emtddd.locationmanagement.domain.valueobjects.Address;
import lombok.Data;

@Data
public class LocationForm {
    private String City;
    private Address address;
}
