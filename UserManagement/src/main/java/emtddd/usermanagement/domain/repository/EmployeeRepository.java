package emtddd.usermanagement.domain.repository;

import emtddd.sharedkernel.domain.valueobjects.Email;
import emtddd.usermanagement.domain.models.Employee;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.base.UserID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, UserID> {
    List<Employee> findAllByLocationId(DomainObjectId locationId);
    Optional<Employee> findByEmail(Email email);
}
