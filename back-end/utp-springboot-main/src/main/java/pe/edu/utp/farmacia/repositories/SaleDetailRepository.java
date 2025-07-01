package pe.edu.utp.farmacia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.farmacia.entity.SaleDetailEntity;

public interface SaleDetailRepository extends JpaRepository<SaleDetailEntity, Integer> {
    // Consultas personalizadas si son necesarias
}