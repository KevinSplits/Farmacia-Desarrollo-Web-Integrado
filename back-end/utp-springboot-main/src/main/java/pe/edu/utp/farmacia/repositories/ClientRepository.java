package pe.edu.utp.farmacia.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import jakarta.transaction.Transactional;
import pe.edu.utp.farmacia.entity.ClientEntity;
import java.util.List;

public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {
    
    @Modifying
    @Transactional
    @Query("UPDATE ClientEntity c SET c.estado = 'Inactivo' WHERE c.idcliente = ?1")
    int invalidateClientById(Integer id);
    
    // Método para contar clientes activos
    @Query("SELECT COUNT(c) FROM ClientEntity c WHERE c.estado = 'Activo'")
    long countActiveClients();
    
    // Método para obtener últimos clientes registrados
    @Query("SELECT c FROM ClientEntity c ORDER BY c.idcliente DESC LIMIT 5")
    List<ClientEntity> findTop5ByOrderByIdclienteDesc();
}