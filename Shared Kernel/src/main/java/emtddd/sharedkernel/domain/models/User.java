package emtddd.sharedkernel.domain.models;

import emtddd.sharedkernel.domain.base.AbstractEntity;
import emtddd.sharedkernel.domain.base.UserID;
import emtddd.sharedkernel.domain.valueobjects.Email;
import jakarta.persistence.Entity;
import lombok.NonNull;

@Entity
public class User extends AbstractEntity<UserID> {
    private String first_name;
    private String last_name;
    private Email email;

    protected User() {
        super(UserID.randomId(UserID.class));
    }

    public User(String first_name, String last_name, Email email) {
        super(UserID.randomId(UserID.class));
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }
}
