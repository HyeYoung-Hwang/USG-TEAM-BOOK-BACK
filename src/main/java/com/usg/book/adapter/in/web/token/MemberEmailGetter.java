package com.usg.book.adapter.in.web.token;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberEmailGetter {

    private final JWTClaimDecoder jwtClaimDecoder;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getMemberEmail(String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        String claims = jwtClaimDecoder.getToken(token);

        try {
            JWTClaimsDto jwtClaimsDto = objectMapper.readValue(claims, JWTClaimsDto.class);
            return jwtClaimsDto.getEmail();
        } catch (Exception e) {
            throw new IllegalArgumentException("Token Claims Not Match");
        }

    }
}
