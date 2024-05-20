package emtddd.vehiclemanagement.config;

import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.vehiclemanagement.domain.models.Status;
import emtddd.vehiclemanagement.domain.models.Vehicle;
import emtddd.vehiclemanagement.domain.repository.VehicleRepository;
import emtddd.vehiclemanagement.service.form.VehicleForm;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final VehicleRepository vehicleRepository;

    @PostConstruct
    public void initData(){
        Vehicle v1 = new Vehicle(new VehicleForm("KU1234AB", "VW GOLF", "qwerty", Status.AVAILABLE,  DomainObjectId.of("1") ));
        Vehicle v2 = new Vehicle(new VehicleForm("KU1122AB", "FORD FOCUS", "asf", Status.AVAILABLE,  DomainObjectId.of("1") ));
        Vehicle v3 = new Vehicle(new VehicleForm("SK2434AB", "ALFA ROMEO BRERA", "zxcvb", Status.AVAILABLE,  DomainObjectId.of("2") ));
        Vehicle v4 = new Vehicle(new VehicleForm("BT1234AB", "SKODA OCTAVIA", "poiuyt", Status.AVAILABLE,  DomainObjectId.of("3") ));

        if(vehicleRepository.findAll().isEmpty()){
            vehicleRepository.saveAll(Arrays.asList(v1, v2, v3, v4));
        }

    }

}
