package emtddd.usermanagement.xport.dto;

import emtddd.sharedkernel.domain.enums.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtAuthenticationDto {
    private String token;
    private String userId;
    private String email;
    private String name;
    private Role role;
}
