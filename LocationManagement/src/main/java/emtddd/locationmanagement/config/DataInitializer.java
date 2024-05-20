package emtddd.locationmanagement.config;

import emtddd.locationmanagement.domain.models.Location;
import emtddd.locationmanagement.domain.repository.LocationRepository;
import emtddd.locationmanagement.domain.valueobjects.Address;
import emtddd.locationmanagement.service.form.LocationForm;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final LocationRepository locationRepository;

    @PostConstruct
    public void initData(){
        Location l1 = new Location(new LocationForm("Kumanovo", new Address("Gjorche Petrov", 17)));
        Location l2 = new Location(new LocationForm("Skopje", new Address("Partizanska", 14)));

        if(locationRepository.findAll().isEmpty()){
            locationRepository.saveAll(Arrays.asList(l1, l2));
        }

    }
}
