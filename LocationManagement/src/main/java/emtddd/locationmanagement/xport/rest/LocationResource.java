package emtddd.locationmanagement.xport.rest;

import emtddd.locationmanagement.domain.models.Location;
import emtddd.locationmanagement.domain.models.LocationID;
import emtddd.locationmanagement.service.LocationService;
import emtddd.sharedkernel.domain.exceptions.InvalidIdException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/id/{locationId}")
    public ResponseEntity<Location> findById(@PathVariable String locationId) {
        try {
            LocationID locationID = new LocationID(locationId);
            Location location = locationService.findById(locationID).orElseThrow(InvalidIdException::new);
            return ResponseEntity.ok(location);
        } catch (InvalidIdException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
