package com.anil.springboot_multiauth.dto;

import io.jsonwebtoken.Claims;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TokenInformationResponseDTO {

    private String email;
    private String authType;
    private Date expiration;
    private String firstName;
    private String lastName;

    public TokenInformationResponseDTO(Claims claims) {
        this.expiration = claims.getExpiration();
        this.authType = claims.get("authType", String.class);
        this.authType = claims.get("firstName", String.class);
        this.authType = claims.get("lastName", String.class);
        this.authType = claims.get("email", String.class);
    }
}
