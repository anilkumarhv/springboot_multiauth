package com.anil.springboot_multiauth.service;

import com.anil.springboot_multiauth.dto.TokenInformationResponseDTO;
import com.anil.springboot_multiauth.model.User;
import com.anil.springboot_multiauth.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class TokenProviderService {

    private final String tokenSecret;

    private final UserRepository userRepository;


    public TokenProviderService(@Value("${jwt.token.secret}") String tokenSecret, UserRepository userRepository) {
        this.tokenSecret = tokenSecret;
        this.userRepository = userRepository;
    }

    public String createJwtToken(String userName, String authType, Map<String, Object> attributes) {

        Optional<User> user = userRepository.findById(userName.toLowerCase());

        String firstName = attributes != null ? (String) attributes.get("firstName") : "";
        String lastName = attributes != null ? (String) attributes.get("lastName") : "";

        if (user.isPresent()) {
            firstName = user.get().getFirstName();
            lastName = user.get().getLastName();
        }

        Date currentDate = new Date();
        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusDays(1);

        Date expires = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        String encodedKey = encode(tokenSecret);

        JwtBuilder builder = Jwts.builder().claim("authType", authType)
                .claim("firstName", firstName).claim("lastName", lastName)
                .claim("email", userName).setId(userName)
                .setIssuedAt(currentDate)
                .setSubject(userName)
                .signWith(signatureAlgorithm, encodedKey)
                .setExpiration(expires);
        return builder.compact();
    }

    private String encode(String decodedStr) {
        return Base64.getEncoder().encodeToString(decodedStr.getBytes());
    }

    public TokenInformationResponseDTO extractInformationFromToken(String token) {
        String secretKey = encode(tokenSecret);
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return new TokenInformationResponseDTO(claims);
    }
}
