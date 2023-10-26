package ra.springboot_security_jwt.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ra.springboot_security_jwt.security.CustomUserDetails;

import java.util.Date;

// Sinh ra JWT
@Component
@Slf4j // ghi log. Annotation cua Lombok
public class JwtTokenProvider {

    @Value("${ra.jwt.secret}")
    private String JWT_SERCRET;

    @Value("${ra.jwt.expiration}")
    private int JWT_EXPIRATION;

    // Tao JWT tu thong tin cua User
    public String generateToken(CustomUserDetails customUserDetails) {
        Date now = new Date();
        Date dateExpired = new Date(now.getTime() + JWT_EXPIRATION);

        // Tao chuoi JWT tu userName
        return Jwts.builder()
                .setSubject(customUserDetails.getUsername())
                .setIssuedAt(now) // Ngay bat dau co hieu luc
                .setExpiration(dateExpired) // Ngay het han
                .signWith(SignatureAlgorithm.HS512, JWT_SERCRET)
                .compact();
    }

    // Lay thong tin user tu JWT
    public String getUserNameFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SERCRET)
                .parseClaimsJws(token).getBody(); // Lay toan bo Claim

        // Tra lai thong tin username
        return claims.getSubject(); // vi tao subject theo name o tren
    }

    // Validate thong tin cua JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SERCRET)
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT Token"); // Khong dung voi Token
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT Token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT Token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims String is empty");
        }
        return false;
    }
}
