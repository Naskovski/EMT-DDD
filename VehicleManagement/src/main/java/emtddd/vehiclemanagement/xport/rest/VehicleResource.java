package emtddd.vehiclemanagement.xport.rest;

import emtddd.vehiclemanagement.domain.models.Vehicle;
import emtddd.vehiclemanagement.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
@AllArgsConstructor
public class VehicleResource {
    private final VehicleService vehicleService;

    @GetMapping("/all")
    public List<Vehicle> getAll(){
        return vehicleService.findAll();
    }
}
