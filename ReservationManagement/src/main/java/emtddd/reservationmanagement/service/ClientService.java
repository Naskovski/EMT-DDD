package emtddd.reservationmanagement.service;

import emtddd.reservationmanagement.domain.models.Client;
import emtddd.reservationmanagement.service.forms.ClientForm;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.base.UserID;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> listAll();
    Optional<Client> findById(UserID id);
    Client create(ClientForm employeeForm);
    Client delete(UserID clientId);
}
