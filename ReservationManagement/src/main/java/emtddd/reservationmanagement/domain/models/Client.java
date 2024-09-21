package emtddd.reservationmanagement.domain.models;

import emtddd.reservationmanagement.service.forms.ClientForm;
import emtddd.sharedkernel.domain.models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "app_user")
@Getter
public class Client extends User {

    private int loyalty_points;

    public Client() {
        this.loyalty_points = 0;
    }

    public Client(ClientForm clientForm) {
        super(clientForm.getFirst_name(), clientForm.getLast_name(), clientForm.getEmail());
        this.loyalty_points = 0;
    }

    public void addPoints() {
        this.loyalty_points++;
    }

    public void resetPoints() {
        this.loyalty_points = 0;
    }
}
