package com.anil.springboot_multiauth.strategy;

import com.anil.springboot_multiauth.config.AuthProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.stereotype.Component;

@Component("okta")
public class OktaClientRegistrationStrategy implements ClientRegistrationStrategy{
    @Value("${okta.client-id}")
    private String oktaClientId;

    @Value("${okta.client-secret}")
    private String oktaClientSecret;

    private static final String authorizationRequestBaseUri = "/oauth2/authorization/okta";

    @Override
    public ClientRegistration clientRegistration() {
        return CommonOAuth2Provider.OKTA.getBuilder(AuthProvider.okta.name())
                .clientId(oktaClientId)
                .clientSecret(oktaClientSecret)
                .build();
    }

    @Override
    public String getLoginUrl() {
        return authorizationRequestBaseUri;
    }
}
