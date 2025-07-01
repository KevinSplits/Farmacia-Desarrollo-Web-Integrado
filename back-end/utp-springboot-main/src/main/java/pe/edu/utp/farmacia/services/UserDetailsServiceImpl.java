package pe.edu.utp.farmacia.services;

import java.util.Optional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.utp.farmacia.entity.UserEntity;
import pe.edu.utp.farmacia.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("==== INTENTO DE LOGIN ====");
        System.out.println("Usuario ingresado: " + username);

        Optional<UserEntity> userOpt = userRepository.findByUsuario(username);

        if (userOpt.isEmpty()) {
            System.out.println("ERROR: No existe usuario con ese nombre");
            // Listar usuarios disponibles para debug
            System.out.println("Usuarios en BD:");
            userRepository.findAll().forEach(u
                    -> System.out.println("- " + u.getUsuario() + " (ID: " + u.getIdusuario() + ")")
            );
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        UserEntity user = userOpt.get();
        System.out.println("Usuario encontrado: " + user.getUsuario());
        System.out.println("Estado: " + user.getEstado());
        System.out.println("Rol: " + user.getTipousuario());

        if (!"Activo".equalsIgnoreCase(user.getEstado())) {
            System.out.println("ERROR: Usuario inactivo");
            throw new UsernameNotFoundException("Usuario inactivo");
        }

        return User.withUsername(user.getUsuario())
                .password(user.getContraseña())
                .roles(user.getTipousuario().replace("ROLE_", ""))
                .build();
    }
}
