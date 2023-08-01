package com.anil.springboot_multiauth.controller;

import com.anil.springboot_multiauth.dto.UserDTO;
import com.anil.springboot_multiauth.service.AuthenticationService;
import com.anil.springboot_multiauth.service.TargetLocationService;
import com.anil.springboot_multiauth.service.TokenProviderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@Slf4j
public class UserAndPasswordAuthenticationController {

    private final TokenProviderService tokenProviderService;
    private final AuthenticationService authenticationService;
    private final TargetLocationService targetLocationService;

    public UserAndPasswordAuthenticationController(TokenProviderService tokenProviderService, AuthenticationService authenticationService, TargetLocationService targetLocationService) {
        this.tokenProviderService = tokenProviderService;
        this.authenticationService = authenticationService;
        this.targetLocationService = targetLocationService;
    }

    @PostMapping("/authenticate")
    public RedirectView authenticate(@Valid @RequestBody UserDTO user, RedirectAttributes redirectAttributes) {
        authenticationService.authenticate(user);
        String token = tokenProviderService.createJwtToken(user.getUsername(), "database", null);
        log.info("generated database token = {}", token);
        redirectAttributes.addAttribute("token", token);
        return new RedirectView(targetLocationService.determineTargetLocation());
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<String> handleException() {
        return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
    }
}
