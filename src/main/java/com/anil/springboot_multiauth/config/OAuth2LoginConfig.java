package com.anil.springboot_multiauth.config;

import com.anil.springboot_multiauth.strategy.ClientRegistrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Configuration
public class OAuth2LoginConfig {

    private final Map<String, ClientRegistrationStrategy> registrationStrategies;

    public OAuth2LoginConfig(Map<String, ClientRegistrationStrategy> registrationStrategies) {
        this.registrationStrategies = registrationStrategies;
    }

    @Bean
    ClientRegistrationRepository clientRegistrationRepository() {

        /*
        * retrieve Authentication provider info from database
        *
        * */
        List<AuthProvider> authProviders = Arrays.asList(AuthProvider.google, AuthProvider.facebook, AuthProvider.keycloak, AuthProvider.github);

        List<ClientRegistration> registrations = authProviders.stream()
                .map(p -> registrationStrategies.get(p.name()).clientRegistration())
                .toList();

        return new InMemoryClientRegistrationRepository(registrations);
    }
}
