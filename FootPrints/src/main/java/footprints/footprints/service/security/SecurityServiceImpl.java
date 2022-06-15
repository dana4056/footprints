package footprints.footprints.service.security;

import footprints.footprints.domain.member.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

@Service
public class SecurityServiceImpl implements SecurityService {
    public String makeJwtToken(Member member) {
        Date now = new Date();

        final String secretKey ="Rm9vdHByaW50cy1IeXVrSmluLURhRXVuLVN1bkppbi1ZdVNlb2stSmlXb24K";
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
                .setIssuer("dana") // (2)
                .setIssuedAt(now) // (3)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // (4)
                .claim("user_id", member.getUser_id()) // (5)
                .claim("nick", member.getNick())
                .claim("email", member.getEmail()) // (5)
                .claim("pw", member.getPw())
                .claim("area", member.getArea())
                .signWith(SignatureAlgorithm.HS256, secretKey) // (6)
                .compact();
    }
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        Claims claims = jwtTokenProvider.parseJwtToken(authorizationHeader);
//
//        filterChain.doFilter(request, response);
//    }

    public Claims parseJwtToken(String authorizationHeader) {
        final String secretKey ="Rm9vdHByaW50cy1IeXVrSmluLURhRXVuLVN1bkppbi1ZdVNlb2stSmlXb24K";
        validationAuthorizationHeader(authorizationHeader); // (1)
        String token = extractToken(authorizationHeader); // (2)

        return Jwts.parser()
                .setSigningKey(secretKey) // (3)
                .parseClaimsJws(token) // (4)
                .getBody();
    }

    private void validationAuthorizationHeader(String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            throw new IllegalArgumentException();
        }
    }

    private String extractToken(String authorizationHeader) {
        return authorizationHeader.substring("Bearer ".length());
    }
}
