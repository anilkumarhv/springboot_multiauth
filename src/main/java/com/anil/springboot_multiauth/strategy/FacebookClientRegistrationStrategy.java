package com.anil.springboot_multiauth.strategy;

import com.anil.springboot_multiauth.config.AuthProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.stereotype.Component;

@Component("facebook")
public class FacebookClientRegistrationStrategy implements ClientRegistrationStrategy {
    @Value("${facebook.client-id}")
    private String facebookClientId;

    @Value("${facebook.client-secret}")
    private String facebookClientSecret;

    private static final String authorizationRequestBaseUri = "/oauth2/authorization/facebook";

    @Override
    public ClientRegistration clientRegistration() {
        return CommonOAuth2Provider.FACEBOOK.getBuilder(AuthProvider.facebook.name())
                .clientId(facebookClientId)
                .clientSecret(facebookClientSecret)
                .build();
    }

    @Override
    public String getLoginUrl() {
        return authorizationRequestBaseUri;
    }
}
