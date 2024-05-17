package emtddd.employeemanagement.domain.repository;

import emtddd.employeemanagement.domain.models.Employee;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.base.UserID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, UserID> {
    List<Employee> findAllByLocationId(DomainObjectId locationId);
}
