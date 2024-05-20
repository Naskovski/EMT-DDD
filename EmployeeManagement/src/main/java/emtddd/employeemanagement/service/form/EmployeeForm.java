package emtddd.employeemanagement.service.form;

import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.valueobjects.Email;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeForm {
    private DomainObjectId location_id;
    private String first_name;
    private String last_name;
    private Email email;
}
