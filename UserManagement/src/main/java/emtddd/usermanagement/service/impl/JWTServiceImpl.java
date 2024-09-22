package emtddd.usermanagement.service.impl;

import emtddd.sharedkernel.domain.models.User;
import emtddd.sharedkernel.domain.valueobjects.Email;
import emtddd.usermanagement.service.CustomUserDetailsService;
import emtddd.usermanagement.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {
    private final CustomUserDetailsService userDetailsService;

    public String generateToken(UserDetails userDetails){
        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        User user = userDetailsService.getUser(new Email(userDetails.getUsername()));
        claims.put("name", user.getName());
        claims.put("userId", user.getId().getId());
        claims.put("role", user.getRole());

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .setClaims(claims)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUserName(String token){
        return extraClaim(token, Claims::getSubject);
    }

    private <T> T extraClaim(String token, Function<Claims,T> claimResolvers){
        final Claims claims = extractAllClaims(token);

        return claimResolvers.apply(claims);
    }

    private Key getSignKey(){
        byte [] key = Decoders.BASE64.decode("413F4428472B4B625065536856605970337336763979244226452948404D6351");
        return Keys.hmacShaKeyFor(key);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username = extractUserName(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token){
        return extraClaim(token,Claims::getExpiration).before(new Date());
    }
}
