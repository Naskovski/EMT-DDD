package emtddd.usermanagement.service;

import emtddd.sharedkernel.domain.base.UserID;
import emtddd.sharedkernel.domain.valueobjects.Email;
import emtddd.usermanagement.domain.models.Client;
import emtddd.usermanagement.xport.dto.UserDetailsDTO;

import java.util.Optional;

public interface ClientService {
    UserDetailsDTO searchByEmail(Email email);
    Optional<Client> findById(UserID userID);
}
