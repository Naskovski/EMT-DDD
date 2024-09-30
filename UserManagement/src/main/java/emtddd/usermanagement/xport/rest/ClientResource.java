package emtddd.usermanagement.xport.rest;

import emtddd.sharedkernel.domain.valueobjects.Email;
import emtddd.usermanagement.service.ClientService;
import emtddd.usermanagement.xport.dto.UserDetailsDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
@AllArgsConstructor
public class ClientResource {
    private final ClientService clientService;

    @GetMapping("/searchByMail")
    public ResponseEntity<UserDetailsDTO> searchByMail(@RequestParam String query){
        try {
            Email email = new Email(query);
            UserDetailsDTO userDetails = clientService.searchByEmail(email);
            return ResponseEntity.ok(userDetails);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
