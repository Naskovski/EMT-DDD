package emtddd.locationmanagement.xport.rest;

import emtddd.locationmanagement.domain.models.Location;
import emtddd.locationmanagement.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/location")
@AllArgsConstructor
public class LocationResource {
    private final LocationService locationService;

    @GetMapping("/all")
    public List<Location> getAll(){
        return locationService.findAll();
    }
}
