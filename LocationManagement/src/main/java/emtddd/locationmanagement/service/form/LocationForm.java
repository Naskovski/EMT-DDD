package emtddd.locationmanagement.service.form;

import emtddd.locationmanagement.domain.valueobjects.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationForm {
    private String City;
    private Address address;
}
