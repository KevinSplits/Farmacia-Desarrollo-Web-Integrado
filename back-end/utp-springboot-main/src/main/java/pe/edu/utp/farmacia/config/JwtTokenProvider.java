package pe.edu.utp.farmacia.config;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.security.Keys; // Importa la clase Keys
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    
    private final SecretKey jwtSecretKey;
    private final int jwtExpirationInMs = 86400000; // 24 horas

    public JwtTokenProvider() {
        // Genera una clave segura para HS512
        this.jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        
        // Opcional: Imprime la clave (solo para desarrollo)
        System.out.println("Clave JWT generada: " + new String(jwtSecretKey.getEncoded()));
    }

    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(jwtSecretKey) // Usa la clave segura
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecretKey) // Usar la nueva variable
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(jwtSecretKey) // Usar la nueva variable
                .build()
                .parseClaimsJws(authToken);
            return true;
        } catch (Exception ex) {
            System.out.println("Error validando token: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}