package emtddd.usermanagement.xport.rest;

import emtddd.usermanagement.exceptions.EmailAlreadyTakenException;
import emtddd.usermanagement.service.AuthService;
import emtddd.usermanagement.service.form.LoginForm;
import emtddd.usermanagement.service.form.UserForm;
import emtddd.usermanagement.xport.dto.JwtAuthenticationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserForm userForm) {
        try {
            JwtAuthenticationDto jwtAuthenticationDto = authService.register(userForm);
            return ResponseEntity.ok(jwtAuthenticationDto);
        } catch (EmailAlreadyTakenException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already taken"); // 409 Conflict
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()); // 500 Internal Server Error
        }
    }


    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationDto> login(@RequestBody LoginForm loginForm) {
        try {
            JwtAuthenticationDto jwtAuthDto = authService.login(loginForm);
            return ResponseEntity.ok(jwtAuthDto);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (DisabledException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        } catch (Exception e) {
            System.err.printf("Login error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
