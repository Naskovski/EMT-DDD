package emtddd.usermanagement.xport.rest;

import emtddd.sharedkernel.domain.base.UserID;
import emtddd.usermanagement.domain.models.Employee;
import emtddd.usermanagement.service.EmployeeService;
import emtddd.usermanagement.xport.dto.UserDetailsDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeResource {
    private final EmployeeService employeeService;

    @GetMapping("/all")
    public List<Employee> getAll(){
        return employeeService.listAll();
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<UserDetailsDTO> findById(@PathVariable String userId){
        try {
            UserID userID = new UserID(userId);
            UserDetailsDTO userDetails = employeeService.findById(userID).map(employee ->
                    new UserDetailsDTO(employee.getId().getId(),
                            employee.getEmail().getEmail(),
                            employee.getName())).orElseThrow(Exception::new);
            return ResponseEntity.ok(userDetails);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
