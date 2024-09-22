package emtddd.usermanagement.service;

import emtddd.sharedkernel.domain.models.User;
import emtddd.sharedkernel.domain.valueobjects.Email;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailsService extends UserDetailsService {
    public User getUser(Email email);
}
