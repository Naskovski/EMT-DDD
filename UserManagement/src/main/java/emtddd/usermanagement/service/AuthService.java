package emtddd.usermanagement.service;

import emtddd.usermanagement.service.form.LoginForm;
import emtddd.usermanagement.service.form.UserForm;
import emtddd.usermanagement.xport.dto.JwtAuthenticationDto;

public interface AuthService {
    JwtAuthenticationDto register(UserForm userForm);
    JwtAuthenticationDto login(LoginForm loginForm);
}
