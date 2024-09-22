package emtddd.usermanagement.service.form;

import emtddd.sharedkernel.domain.valueobjects.Email;
import emtddd.sharedkernel.domain.valueobjects.Password;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserForm {
    private String name;
    private Email email;
    private Password password;
}
