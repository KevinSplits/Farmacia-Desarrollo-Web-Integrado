package pe.edu.utp.farmacia.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import jakarta.transaction.Transactional;
import pe.edu.utp.farmacia.entity.EmployeeEntity;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE EmployeeEntity e SET e.estado = 'Inactivo' WHERE e.idempleado = ?1")
    int invalidateEmployeeById(Integer id);
    
    // Método para contar empleados activos
    @Query("SELECT COUNT(e) FROM EmployeeEntity e WHERE e.estado = 'Activo'")
    long countActiveEmployees();
}