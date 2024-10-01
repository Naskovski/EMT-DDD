package emtddd.usermanagement.service.form;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import emtddd.sharedkernel.domain.valueobjects.Email;
import emtddd.sharedkernel.domain.valueobjects.Password;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginForm {
    private Email email;
    private Password password;

    @JsonCreator
    public LoginForm(@JsonProperty("email") String email,
                     @JsonProperty("password") String password){
        this.email = new Email(email);
        this.password = new Password(password);
    }
}
