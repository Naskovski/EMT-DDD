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


}
