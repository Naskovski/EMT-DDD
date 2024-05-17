package emtddd.reservationmanagement.domain.models;

import emtddd.sharedkernel.domain.models.User;
import jakarta.persistence.Entity;


public class Client extends User {

    private int loyalty_points;

    public Client() {
        this.loyalty_points = 0;
    }
}
