package emtddd.usermanagement.service.form;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.valueobjects.Email;
import emtddd.sharedkernel.domain.valueobjects.Password;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeForm {
    private DomainObjectId location_id;
    private String name;
    private Email email;
    private Password password;

    @JsonCreator
    public EmployeeForm(@JsonProperty("location_id") String location_id,
                    @JsonProperty("name") String name,
                    @JsonProperty("email") String email,
                    @JsonProperty("password") String password) {
        this.location_id = new DomainObjectId(location_id);
        this.name = name;
        this.email = new Email(email);
        this.password = new Password(password);
    }
}
