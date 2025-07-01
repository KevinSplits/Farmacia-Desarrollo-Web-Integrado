package pe.edu.utp.farmacia.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;
import pe.edu.utp.farmacia.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.estado = 'Inactivo' WHERE u.idusuario = ?1")
    int invalidateUserById(Integer idusuario);

    // ✅ Necesario para login
    Optional<UserEntity> findByUsuario(String usuario);
}