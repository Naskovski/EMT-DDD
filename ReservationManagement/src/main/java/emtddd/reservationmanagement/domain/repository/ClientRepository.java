package emtddd.reservationmanagement.domain.repository;

import emtddd.reservationmanagement.domain.models.Client;
import emtddd.sharedkernel.domain.base.UserID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, UserID> {
}
