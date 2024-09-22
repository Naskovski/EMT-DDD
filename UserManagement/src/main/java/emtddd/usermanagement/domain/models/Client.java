package emtddd.usermanagement.domain.models;


import emtddd.sharedkernel.domain.enums.Role;
import emtddd.usermanagement.service.form.UserForm;
import emtddd.sharedkernel.domain.models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "client")
@Getter
public class Client extends User {

    private int loyalty_points;

    public Client() {
        this.loyalty_points = 0;
    }

    public Client(UserForm userForm) {
        super(userForm.getName(), userForm.getEmail(), userForm.getPassword(), Role.CLIENT);
        this.loyalty_points = 0;
    }

    public void addPoints() {
        this.loyalty_points++;
    }

    public void resetPoints() {
        this.loyalty_points = 0;
    }
}
