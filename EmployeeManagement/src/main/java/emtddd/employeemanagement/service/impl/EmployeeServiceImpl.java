package emtddd.employeemanagement.service.impl;

import emtddd.employeemanagement.domain.models.Employee;
import emtddd.employeemanagement.domain.repository.EmployeeRepository;
import emtddd.employeemanagement.service.EmployeeService;
import emtddd.employeemanagement.service.form.EmployeeForm;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.base.UserID;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> listAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findAllByLocation(DomainObjectId location_id) {
        return employeeRepository.findAllByLocationId(location_id);
    }

    @Override
    public Optional<Employee> findById(UserID id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee create(EmployeeForm employeeForm) {
        return employeeRepository.save(new Employee(employeeForm));
    }

    @Override
    public Employee relocate(Employee employee, DomainObjectId newLocationId) {
        employee.relocate(newLocationId);
        return employeeRepository.save(employee);
    }
}
