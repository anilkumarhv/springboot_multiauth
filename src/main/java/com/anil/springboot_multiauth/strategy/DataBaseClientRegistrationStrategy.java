package com.anil.springboot_multiauth.strategy;

import com.anil.springboot_multiauth.dto.UserDTO;
import com.anil.springboot_multiauth.service.AuthenticationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Component;

@Component("database")
public class DataBaseClientRegistrationStrategy implements ClientRegistrationStrategy, AuthenticationService {

    private static final String authorizationRequestBaseUri = "/authenticate";

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public DataBaseClientRegistrationStrategy(AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @Override
    public void authenticate(UserDTO user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        authenticationManagerBuilder.getObject().authenticate(token);
    }

    @Override
    public String getLoginUrl() {
        return authorizationRequestBaseUri;
    }
}
