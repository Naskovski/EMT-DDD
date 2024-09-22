package emtddd.sharedkernel.domain.models;

import emtddd.sharedkernel.domain.base.AbstractEntity;
import emtddd.sharedkernel.domain.base.UserID;
import emtddd.sharedkernel.domain.enums.Role;
import emtddd.sharedkernel.domain.valueobjects.Email;
import emtddd.sharedkernel.domain.valueobjects.Password;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class User extends AbstractEntity<UserID>{
    private String name;
    private Email email;
    private Password password;
    @Enumerated
    private Role role;

    protected User() {
        super(UserID.randomId(UserID.class));
    }

    public User(String name, Email email, Password password, Role role) {
        super(UserID.randomId(UserID.class));
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
