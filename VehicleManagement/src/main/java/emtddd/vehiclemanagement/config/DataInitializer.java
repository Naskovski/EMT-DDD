package emtddd.vehiclemanagement.config;

import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.valueobjects.Money;
import emtddd.vehiclemanagement.domain.models.Status;
import emtddd.vehiclemanagement.domain.models.Vehicle;
import emtddd.vehiclemanagement.domain.repository.VehicleRepository;
import emtddd.vehiclemanagement.domain.valueobjects.LocationID;
import emtddd.vehiclemanagement.service.form.VehicleForm;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final VehicleRepository vehicleRepository;

    //@PostConstruct
    public void initData(){
        Vehicle v1 = new Vehicle(new VehicleForm("KU1234AB", "VW GOLF", "qwerty", Status.AVAILABLE,  new LocationID("ec04dc5d-758c-414d-a47e-b94ceed44413"), new Money(60.0) ));
        Vehicle v2 = new Vehicle(new VehicleForm("KU1122AB", "FORD FOCUS", "asf", Status.AVAILABLE,  new LocationID("ecf187b4-f056-4fa6-8a23-b9cb0dce1e6f"), new Money(50.0) ));
        Vehicle v3 = new Vehicle(new VehicleForm("SK2434AB", "ALFA ROMEO BRERA", "zxcvb", Status.AVAILABLE,  new LocationID("ecf187b4-f056-4fa6-8a23-b9cb0dce1e6f"), new Money(40.0) ));
        Vehicle v4 = new Vehicle(new VehicleForm("KU1222AB", "VW POLO", "qwerty", Status.AVAILABLE,  new LocationID("ec04dc5d-758c-414d-a47e-b94ceed44413"), new Money(60.0) ));
        Vehicle v5 = new Vehicle(new VehicleForm("KU1122AB", "FORD FIESTA", "asf", Status.AVAILABLE,  new LocationID("ecf187b4-f056-4fa6-8a23-b9cb0dce1e6f"), new Money(50.0) ));
        Vehicle v6 = new Vehicle(new VehicleForm("SK2444AB", "ALFA ROMEO 159", "zxcvb", Status.AVAILABLE,  new LocationID("ec04dc5d-758c-414d-a47e-b94ceed44413"), new Money(40.0) ));
        Vehicle v7 = new Vehicle(new VehicleForm("BT1111AB", "AUDI A5", "poiuyt", Status.AVAILABLE,  new LocationID("ecf187b4-f056-4fa6-8a23-b9cb0dce1e6f"), new Money(80.0) ));

        if(vehicleRepository.findAll().isEmpty()){
            vehicleRepository.saveAll(Arrays.asList(v1, v2, v3, v4, v5, v6, v7));
        }

    }

}
