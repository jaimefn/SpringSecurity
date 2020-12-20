package br.com.nextmove.condonext.service.security;

import br.com.nextmove.condonext.domain.user.User;
import br.com.nextmove.condonext.dto.auth.TokenDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.expiration.time}")
    private String jwtExpirationTime;
    @Value("${jwt.secret}")
    private String jwtSecret;


    public TokenDTO getToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Instant instant = Instant.now();
        Date createdDate = Date.from(instant);
        Date expirationDate = Date.from(instant.plusSeconds(Long.valueOf(jwtExpirationTime)));
        String token = Jwts.builder()
                .setIssuer("Api User CondoNext")
                .setSubject(user.getId().toString())
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256,jwtSecret)
                .compact();
        return new TokenDTO(token,"Bearer",Long.valueOf(jwtExpirationTime));
    }

    public Boolean isValidToken(String token) {
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (Exception ex){
            System.out.println("token validation error!");
        }
        return false;
    }

    public Long getUserId(String token) {
            return Long.valueOf(Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject());
    }
}
