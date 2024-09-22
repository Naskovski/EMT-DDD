package emtddd.usermanagement.domain.models;

import emtddd.sharedkernel.domain.enums.Role;
import emtddd.usermanagement.service.form.EmployeeForm;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.models.User;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "employee")
@Getter
@NoArgsConstructor
public class Employee extends User {

    @AttributeOverride(name = "id", column = @Column(name = "location_id"))
    private DomainObjectId locationId;

    public Employee(EmployeeForm employeeForm) {
        super(employeeForm.getName(), employeeForm.getEmail(), employeeForm.getPassword(), Role.EMPLOYEE);
        this.locationId = employeeForm.getLocation_id();
    }

    public DomainObjectId relocate(DomainObjectId new_location_id) {
        this.locationId = new_location_id;
        return this.locationId;
    }

}
