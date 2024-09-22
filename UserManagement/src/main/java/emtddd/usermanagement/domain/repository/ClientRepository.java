package emtddd.usermanagement.domain.repository;


import emtddd.sharedkernel.domain.base.UserID;
import emtddd.sharedkernel.domain.valueobjects.Email;
import emtddd.usermanagement.domain.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, UserID> {
    Optional<Client> findByEmail(Email email);
}
