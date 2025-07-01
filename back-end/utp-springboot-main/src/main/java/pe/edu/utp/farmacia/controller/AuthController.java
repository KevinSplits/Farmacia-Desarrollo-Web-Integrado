package pe.edu.utp.farmacia.controller;

import java.util.Collection;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.utp.farmacia.config.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("Credenciales recibidas - Usuario: " + loginRequest.getUsername());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtTokenProvider.generateToken(authentication);

            System.out.println("Token generado: " + token);

            return ResponseEntity.ok().body(
                    new LoginResponse(
                            true,
                            "Login exitoso",
                            token,
                            userDetails.getUsername(),
                            userDetails.getAuthorities() // Añade los roles
                    )
            );

        } catch (Exception e) {
            System.out.println("Error de autenticación: " + e.getMessage());
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "error", "Credenciales inválidas",
                            "message", e.getMessage()
                    )
            );
        }
    }
}

class LoginRequest {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class LoginResponse {
    private boolean success;
    private String message;
    private String token;
    private String username;
    private Collection<? extends GrantedAuthority> roles;

    // Constructor y getters
    public LoginResponse(boolean success, String message, String token, 
                        String username, Collection<? extends GrantedAuthority> roles) {
        this.success = success;
        this.message = message;
        this.token = token;
        this.username = username;
        this.roles = roles;
    }
    
    // Añade getter para roles
    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }
}