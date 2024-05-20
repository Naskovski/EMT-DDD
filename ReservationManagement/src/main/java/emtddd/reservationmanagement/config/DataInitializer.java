package emtddd.reservationmanagement.config;

import emtddd.reservationmanagement.domain.models.Client;
import emtddd.reservationmanagement.domain.repository.ClientRepository;
import emtddd.reservationmanagement.service.forms.ClientForm;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.valueobjects.Email;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final ClientRepository clientRepository;

    @PostConstruct
    public void initData(){
        Client c1 = new Client(new ClientForm("Filip", "Naskovski", new Email("filip@gmail.com")));
        Client c2 = new Client(new ClientForm("Marija", "Marija", new Email("marija@gmail.com")));
        Client c3 = new Client(new ClientForm("Ivan", "Ivanovski", new Email("ivvan@gmail.com")));

        if(clientRepository.findAll().isEmpty()){
            clientRepository.saveAll(Arrays.asList(c1, c2, c3));
        }

    }
}
