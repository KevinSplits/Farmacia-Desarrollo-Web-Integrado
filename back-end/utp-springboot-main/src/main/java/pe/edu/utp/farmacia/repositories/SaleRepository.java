package pe.edu.utp.farmacia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.farmacia.entity.SaleEntity;

public interface SaleRepository extends JpaRepository<SaleEntity, Integer> {
    // Consultas personalizadas si son necesarias
}