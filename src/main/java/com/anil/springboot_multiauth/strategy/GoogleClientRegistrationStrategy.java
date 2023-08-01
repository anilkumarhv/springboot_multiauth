package com.anil.springboot_multiauth.strategy;

import com.anil.springboot_multiauth.config.AuthProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.stereotype.Component;

@Component("google")
public class GoogleClientRegistrationStrategy implements ClientRegistrationStrategy {

    private static final String authorizationRequestBaseUri = "/oauth2/authorization/google";

    @Value("${google.client-id}")
    private String googleClientId;

    @Value("${google.client-secret}")
    private String googleClientSecret;


    @Override
    public ClientRegistration clientRegistration() {
        return CommonOAuth2Provider.GOOGLE.getBuilder(AuthProvider.google.name())
                .clientId(googleClientId)
                .clientSecret(googleClientSecret)
                .build();
    }

    @Override
    public String getLoginUrl() {
        return authorizationRequestBaseUri;
    }
}
