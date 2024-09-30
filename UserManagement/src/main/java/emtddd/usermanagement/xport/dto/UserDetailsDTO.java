package emtddd.usermanagement.xport.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDetailsDTO{
    private String userId;
    private String email;
    private String name;
}
