package emtddd.reservationmanagement.xport.client;

import emtddd.reservationmanagement.domain.valueobjects.Location;
import emtddd.reservationmanagement.domain.valueobjects.LocationID;
import emtddd.sharedkernel.domain.valueobjects.Address;
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
public class LocationClient {
    private final RestTemplate restTemplate;
    private final String serverUrl;

    public LocationClient(@Value("${app.location-management.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<Location> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/location/all").build().toUri(), HttpMethod.GET,null, new ParameterizedTypeReference<List<Location>>() {
            }).getBody();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    public Location findById(LocationID locationID){
        try {
            return restTemplate.exchange(uri().path("/api/location/id/"+locationID.getId()).build().toUri(), HttpMethod.GET,null, new ParameterizedTypeReference<Location>() {
            }).getBody();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new Location(locationID, null, null);
        }
    }
}
