package emtddd.usermanagement.service.impl;

import emtddd.sharedkernel.domain.models.User;
import emtddd.sharedkernel.domain.valueobjects.Email;
import emtddd.usermanagement.domain.models.Client;
import emtddd.usermanagement.domain.models.CustomUserDetails;
import emtddd.usermanagement.domain.models.Employee;
import emtddd.usermanagement.domain.repository.ClientRepository;
import emtddd.usermanagement.domain.repository.EmployeeRepository;
import emtddd.usermanagement.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements CustomUserDetailsService {
    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;

    @Override
    public User getUser(Email email){
        Optional<Employee> employeeOpt = employeeRepository.findByEmail(email);
        if (employeeOpt.isPresent()) {
            return employeeOpt.get();
        }

        Optional<Client> clientOpt = clientRepository.findByEmail(email);
        if (clientOpt.isPresent()) {
            return clientOpt.get();
        }

        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return employeeRepository.findByEmail(new Email(email))
                .map(CustomUserDetails::new)
                .or(() -> clientRepository.findByEmail(new Email(email)).map(CustomUserDetails::new))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
