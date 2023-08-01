package com.anil.springboot_multiauth.service;

import com.anil.springboot_multiauth.config.AuthProvider;
import com.anil.springboot_multiauth.dto.AuthenticationResponseDTO;
import com.anil.springboot_multiauth.strategy.ClientRegistrationStrategy;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ClientRegistrationLoginService {

    private final Map<String, ClientRegistrationStrategy> clientRegistrationStrategy;

    public ClientRegistrationLoginService(Map<String, ClientRegistrationStrategy> clientRegistrationStrategy) {
        this.clientRegistrationStrategy = clientRegistrationStrategy;
    }

    public List<AuthenticationResponseDTO> getLoginUrls() {
        return Arrays.stream(AuthProvider.values()).filter(ap -> !"okta".equals(ap.name())).map(ap -> new AuthenticationResponseDTO(clientRegistrationStrategy.get(ap.name()).getLoginUrl(), ap.name())).toList();
//        return clientRegistrationStrategy.values().stream().map(r -> new AuthenticationResponseDTO(r.getLoginUrl(), r.clientRegistration().getClientName())).toList();
    }
}
