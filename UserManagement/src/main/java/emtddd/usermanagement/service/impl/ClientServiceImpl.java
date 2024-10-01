package emtddd.usermanagement.service.impl;

import emtddd.sharedkernel.domain.base.UserID;
import emtddd.sharedkernel.domain.models.User;
import emtddd.sharedkernel.domain.valueobjects.Email;
import emtddd.usermanagement.domain.models.Client;
import emtddd.usermanagement.domain.repository.ClientRepository;
import emtddd.usermanagement.service.ClientService;
import emtddd.usermanagement.service.CustomUserDetailsService;
import emtddd.usermanagement.xport.dto.UserDetailsDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final CustomUserDetailsService userDetailsService;
    private final ClientRepository clientRepository;

    @Override
    public UserDetailsDTO searchByEmail(Email email) {
        User user = userDetailsService.getUser(email);
        return new UserDetailsDTO(user.getId().getId(), user.getEmail().getEmail(), user.getName());
    }

    @Override
    public Optional<Client> findById(UserID userID) {
        return clientRepository.findById(userID);
    }
}
