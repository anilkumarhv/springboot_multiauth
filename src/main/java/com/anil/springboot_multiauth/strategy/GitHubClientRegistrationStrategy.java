package com.anil.springboot_multiauth.strategy;

import com.anil.springboot_multiauth.config.AuthProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.stereotype.Component;

@Component("github")
public class GitHubClientRegistrationStrategy implements ClientRegistrationStrategy {

    @Value("${github.client-id}")
    private String githubClientId;

    @Value("${github.client-secret}")
    private String githubClientSecret;

    private static final String authorizationRequestBaseUri = "/oauth2/authorization/github";

    @Override
    public ClientRegistration clientRegistration() {
        return CommonOAuth2Provider.GITHUB.getBuilder(AuthProvider.github.name())
                .clientId(githubClientId)
                .clientSecret(githubClientSecret)
                .build();
    }

    @Override
    public String getLoginUrl() {
        return authorizationRequestBaseUri;
    }
}
