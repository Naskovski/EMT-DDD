package emtddd.sharedkernel.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import emtddd.sharedkernel.domain.base.ValueObject;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Email implements ValueObject {
    private String email;

    @JsonCreator
    public Email(@JsonProperty("email") String email) {
        this.email = email;
    }

    @JsonValue
    public String getEmail() {
        return email;
    }
}
