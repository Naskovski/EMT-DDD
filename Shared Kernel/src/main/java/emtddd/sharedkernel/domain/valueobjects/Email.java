package emtddd.sharedkernel.domain.valueobjects;

import emtddd.sharedkernel.domain.base.ValueObject;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Email implements ValueObject {
    private String email;

    public Email(String email) {
        this.email = email;
    }

    public Email() {

    }
}
