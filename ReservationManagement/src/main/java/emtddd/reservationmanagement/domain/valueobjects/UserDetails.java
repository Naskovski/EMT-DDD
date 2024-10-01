package emtddd.reservationmanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import emtddd.sharedkernel.domain.base.UserID;
import emtddd.sharedkernel.domain.base.ValueObject;
import emtddd.sharedkernel.domain.valueobjects.Email;
import lombok.Getter;

@Getter
public class UserDetails implements ValueObject {
    private final UserID userId;
    private final Email email;
    private final String name;

    @JsonCreator
    public UserDetails(@JsonProperty("userId") String userId,
                       @JsonProperty("email") String email,
                       @JsonProperty("name") String name) {
        this.userId = new UserID(userId);
        this.email = new Email(email);
        this.name = name;
    }
}
