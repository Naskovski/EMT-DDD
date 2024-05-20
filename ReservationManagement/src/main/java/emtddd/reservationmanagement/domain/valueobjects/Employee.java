package emtddd.reservationmanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.base.UserID;
import emtddd.sharedkernel.domain.base.ValueObject;
import emtddd.sharedkernel.domain.valueobjects.Email;
import lombok.Getter;

@Getter
public class Employee implements ValueObject {

    private UserID userID;
    private LocationID locationId;
    private String first_name;
    private String last_name;
    private Email email;

    @JsonCreator
    public Employee(UserID userID, LocationID locationId, String first_name, String last_name, Email email) {
        this.userID = userID;
        this.locationId = locationId;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }
}
