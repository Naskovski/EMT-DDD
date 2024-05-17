package emtddd.reservationmanagement.service.impl;

import emtddd.reservationmanagement.domain.exceptions.InvalidClientIdException;
import emtddd.reservationmanagement.domain.models.Client;
import emtddd.reservationmanagement.domain.repository.ClientRepository;
import emtddd.reservationmanagement.service.ClientService;
import emtddd.reservationmanagement.service.forms.ClientForm;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.base.UserID;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public List<Client> listAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> findById(UserID id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client create(ClientForm employeeForm) {
        return clientRepository.save(new Client(employeeForm));
    }

    @Override
    public Client delete(UserID clientId) {
        Client client = findById(clientId).orElseThrow(InvalidClientIdException::new);
        clientRepository.delete(client);
        return client;
    }
}
