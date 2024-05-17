package emtddd.employeemanagement.service;

import emtddd.employeemanagement.domain.models.Employee;
import emtddd.employeemanagement.service.form.EmployeeForm;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.base.UserID;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> listAll();
    List<Employee> findAllByLocation(DomainObjectId location_id);
    Optional<Employee> findById(UserID id);
    Employee create(EmployeeForm employeeForm);
    Employee relocate(Employee employee, DomainObjectId NewLocationId);
}
