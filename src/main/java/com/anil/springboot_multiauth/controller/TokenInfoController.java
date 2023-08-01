package com.anil.springboot_multiauth.controller;

import com.anil.springboot_multiauth.dto.TokenInformationResponseDTO;
import com.anil.springboot_multiauth.service.TokenProviderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenInfoController {
    private final TokenProviderService tokenProviderService;


    public TokenInfoController(TokenProviderService tokenProviderService) {
        this.tokenProviderService = tokenProviderService;
    }

    @GetMapping("/token-info")
    public ResponseEntity<TokenInformationResponseDTO> tokenInfo(String token) {
        try {
            return new ResponseEntity<>(tokenProviderService.extractInformationFromToken(token), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
