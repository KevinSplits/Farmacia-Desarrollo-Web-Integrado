package pe.edu.utp.farmacia.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;
import pe.edu.utp.farmacia.entity.ClientEntity;

public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {
    
    @Modifying
    @Transactional
    @Query("UPDATE ClientEntity c SET c.estado = 'Inactivo' WHERE c.idcliente = ?1")
    int invalidateClientById(Integer id);
}
