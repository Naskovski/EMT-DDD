package emtddd.usermanagement.service.impl;

import emtddd.sharedkernel.domain.valueobjects.Password;
import emtddd.usermanagement.domain.models.Client;
import emtddd.usermanagement.domain.repository.ClientRepository;
import emtddd.usermanagement.exceptions.EmailAlreadyTakenException;
import emtddd.usermanagement.service.AuthService;
import emtddd.usermanagement.service.CustomUserDetailsService;
import emtddd.usermanagement.service.JWTService;
import emtddd.usermanagement.service.form.LoginForm;
import emtddd.usermanagement.service.form.UserForm;
import emtddd.usermanagement.xport.dto.JwtAuthenticationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final ClientRepository clientRepository;

    public JwtAuthenticationDto register(UserForm userForm) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userForm.getEmail().getEmail());
            throw new EmailAlreadyTakenException();
        } catch (UsernameNotFoundException e) {
            UserForm userFormWithEncodedPassword = new UserForm(
                    userForm.getName(),
                    userForm.getEmail(),
                    new Password(passwordEncoder.encode(userForm.getPassword().getPassword()))
            );
            Client user = new Client(userFormWithEncodedPassword);
            clientRepository.save(user);//todo inspect functionality
            return login(
                    new LoginForm(
                            userForm.getEmail(),
                            userForm.getPassword()
                    )
            );
        }
    }


    public JwtAuthenticationDto login(LoginForm loginForm) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail().getEmail(), loginForm.getPassword().getPassword()));
        var user = userDetailsService.loadUserByUsername(loginForm.getEmail().getEmail());
        var jwt = jwtService.generateToken(user);
        var userEntity = userDetailsService.getUser(loginForm.getEmail());
        JwtAuthenticationDto jwtAuthenticationDto = new JwtAuthenticationDto();
        jwtAuthenticationDto.setToken(jwt);
        jwtAuthenticationDto.setEmail(loginForm.getEmail().getEmail());
        jwtAuthenticationDto.setRole(userEntity.getRole());
        jwtAuthenticationDto.setName(userEntity.getName());
        jwtAuthenticationDto.setUserId(userEntity.getId().getId());

        return jwtAuthenticationDto;
    }
}
