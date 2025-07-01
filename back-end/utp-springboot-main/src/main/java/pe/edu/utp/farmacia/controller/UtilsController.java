package pe.edu.utp.farmacia.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/utils")
public class UtilsController {
    
    private final PasswordEncoder passwordEncoder;
    
    public UtilsController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    @GetMapping("/hash")
    public String generateHash(@RequestParam String plainText) {
        String hash = passwordEncoder.encode(plainText);
        System.out.println("Texto plano: " + plainText);
        System.out.println("Hash generado: " + hash);
        return hash;
    }
}