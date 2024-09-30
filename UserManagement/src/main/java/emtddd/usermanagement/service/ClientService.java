package emtddd.usermanagement.service;

import emtddd.sharedkernel.domain.valueobjects.Email;
import emtddd.usermanagement.xport.dto.UserDetailsDTO;

public interface ClientService {
    UserDetailsDTO searchByEmail(Email email);
}
