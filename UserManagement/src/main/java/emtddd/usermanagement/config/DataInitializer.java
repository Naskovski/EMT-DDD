package emtddd.usermanagement.config;

import emtddd.sharedkernel.domain.valueobjects.Password;
import emtddd.usermanagement.domain.models.Employee;
import emtddd.usermanagement.domain.repository.EmployeeRepository;
import emtddd.usermanagement.domain.valueobjects.Location;
import emtddd.usermanagement.service.form.EmployeeForm;
import emtddd.sharedkernel.domain.valueobjects.Email;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

//@Component
@AllArgsConstructor
public class DataInitializer {
    private final EmployeeRepository employeeRepository;

    private Location[] retrieveLocations() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9093/api/location/all";
        return restTemplate.getForObject(url, Location[].class);
    }

    @PostConstruct
    public void initData(){
        Location[] locations = retrieveLocations();
        Employee l1 = new Employee(new EmployeeForm(locations[0].getLocationID(), "Petar Petrev", new Email("petar@yahoo.com"), new Password("password")));
        Employee l2 = new Employee(new EmployeeForm(locations[1].getLocationID(), "Ivana Stojanovska", new Email("iv@yahoo.com"), new Password("password")));
        Employee l3 = new Employee(new EmployeeForm(locations[0].getLocationID(), "Jovana Petrev", new Email("jj@yahoo.com"), new Password("password")));
        Employee l4 = new Employee(new EmployeeForm(locations[1].getLocationID(), "Dejan Petrev", new Email("dj@yahoo.com"), new Password("password")));


        if(employeeRepository.findAll().isEmpty()){
            employeeRepository.saveAll(Arrays.asList(l1, l2, l3, l4));
        }

    }
}
