package com.anil.springboot_multiauth.controller;

import com.anil.springboot_multiauth.dto.AuthenticationResponseDTO;
import com.anil.springboot_multiauth.service.ClientRegistrationLoginService;
import com.anil.springboot_multiauth.service.TokenProviderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthController {

    private final String targetLocation;

    private final TokenProviderService tokenProviderService;

    private final ClientRegistrationLoginService clientRegistrationLoginService;

    public AuthController(@Value("${client.target.url}") String targetLocation, TokenProviderService tokenProviderService, ClientRegistrationLoginService clientRegistrationLoginService) {
        this.targetLocation = targetLocation;
        this.tokenProviderService = tokenProviderService;
        this.clientRegistrationLoginService = clientRegistrationLoginService;
    }

    @GetMapping("/")
    public RedirectView OAuthLogin(@AuthenticationPrincipal OidcUser user, RedirectAttributes attributes) {
        String emailOrName = user.getAttribute("email") != null ? user.getAttribute("email") : user.getName();

//        String token = tokenProviderService.createJwtToken(emailOrName, "oauth2", user.getAttributes());

        attributes.addAttribute("token", user.getIdToken().getTokenValue());
        System.out.println(user.getIdToken().getTokenValue());
        return new RedirectView(targetLocation);
    }

    @GetMapping("/authentication-urls")
    public List<AuthenticationResponseDTO> authenticationUrl(String email) {
        List<AuthenticationResponseDTO> urls = new ArrayList<>();
        if (email != null) {
            urls = clientRegistrationLoginService.getLoginUrls();
        }
        return urls;
    }
}
