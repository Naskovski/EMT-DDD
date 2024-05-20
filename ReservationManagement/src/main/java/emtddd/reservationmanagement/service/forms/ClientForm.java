package emtddd.reservationmanagement.service.forms;

import emtddd.sharedkernel.domain.valueobjects.Email;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientForm {
    private String first_name;
    private String last_name;
    private Email email;
}
