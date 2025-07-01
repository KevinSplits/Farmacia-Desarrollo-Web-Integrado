package pe.edu.utp.farmacia.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;
import pe.edu.utp.farmacia.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    
    // Método para autenticación
    @Query("SELECT u FROM UserEntity u WHERE LOWER(u.usuario) = LOWER(:username)")
    Optional<UserEntity> findByUsuario(@Param("username") String username);
    
    // Método existente para desactivar usuarios
    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.estado = 'Inactivo' WHERE u.idusuario = ?1")
    int invalidateUserById(Integer idusuario);
}