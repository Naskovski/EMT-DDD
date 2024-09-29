package emtddd.vehiclemanagement.xport.rest;

import emtddd.vehiclemanagement.domain.models.Vehicle;
import emtddd.vehiclemanagement.domain.models.VehicleID;
import emtddd.vehiclemanagement.service.VehicleService;
import emtddd.vehiclemanagement.xport.dto.VehicleDto;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicle")
@AllArgsConstructor
public class VehicleResource {
    private final VehicleService vehicleService;

    @GetMapping("/all")
    public List<VehicleDto> getAll(){
        return vehicleService.findAll();
    }

    @GetMapping("/available")
    public List<VehicleDto> getAvailable(){
        return vehicleService.findAllAvailable();
    }

    @GetMapping("/{vehicle_id}/available_dates")
    public ResponseEntity<List<LocalDate>> getAvailableDates(
            @PathVariable("vehicle_id") String vehicleId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        Optional<Vehicle> vehicleOpt = vehicleService.findById(new VehicleID(vehicleId));

        if (vehicleOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Vehicle vehicle = vehicleOpt.get();
        List<LocalDate> availableDates = vehicleService.getAvailableDays(vehicle, startDate, endDate);
        return ResponseEntity.ok(availableDates);
    }
}
