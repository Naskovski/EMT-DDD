package emtddd.usermanagement.service.form;

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
}
