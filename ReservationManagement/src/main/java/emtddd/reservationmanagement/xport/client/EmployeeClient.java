package emtddd.reservationmanagement.xport.client;

import emtddd.reservationmanagement.domain.valueobjects.Employee;
import emtddd.reservationmanagement.domain.valueobjects.Vehicle;
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
            return Collections.emptyList();
        }
    }

}
