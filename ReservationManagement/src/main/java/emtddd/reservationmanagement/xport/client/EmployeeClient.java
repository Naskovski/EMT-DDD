package emtddd.reservationmanagement.xport.client;

import emtddd.reservationmanagement.domain.valueobjects.Employee;
import emtddd.reservationmanagement.domain.valueobjects.UserDetails;
import emtddd.sharedkernel.domain.base.UserID;
import emtddd.sharedkernel.domain.valueobjects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;


@Service
public class EmployeeClient {
    private final RestTemplate restTemplate;
    private final String serverUrl;

    public EmployeeClient(@Value("${app.employee-management.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<Employee> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/employee/all").build().toUri(), HttpMethod.GET,null, new ParameterizedTypeReference<List<Employee>>() {
            }).getBody();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    public UserDetails findById(UserID userID){
        try {
            return restTemplate.exchange(uri().path("/api/employee/id/"+userID.getId()).build().toUri(), HttpMethod.GET,null, new ParameterizedTypeReference<UserDetails>() {
            }).getBody();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new UserDetails(userID.getId(), null, null);
        }
    }

}
