package pe.edu.utp.farmacia.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import jakarta.transaction.Transactional;
import pe.edu.utp.farmacia.entity.ProductEntity;
import java.util.List;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE ProductEntity p SET p.estado = 'INACTIVO' WHERE p.idproducto = ?1")
    int invalidateProductById(Integer idProducto);

    @Query("SELECT p FROM ProductEntity p WHERE UPPER(p.estado) = 'ACTIVO'")
    List<ProductEntity> findAvailableProducts();

    @Query("SELECT p FROM ProductEntity p WHERE p.stock < :stockThreshold AND UPPER(p.estado) = 'ACTIVO'")
    List<ProductEntity> findByStockLessThan(@Param("stockThreshold") int stockThreshold);

    @Query("SELECT p FROM ProductEntity p WHERE UPPER(p.descripcion) LIKE UPPER(CONCAT('%', :searchTerm, '%')) AND UPPER(p.estado) = 'ACTIVO'")
    List<ProductEntity> searchAvailableProducts(@Param("searchTerm") String searchTerm);

    @Query("SELECT COUNT(p) FROM ProductEntity p WHERE UPPER(p.estado) = 'ACTIVO'")
    long countActiveProducts();
    
    // Listar productos inactivos
    @Query("SELECT p FROM ProductEntity p WHERE UPPER(p.estado) = 'INACTIVO'")
    List<ProductEntity> findInactiveProducts();

    // Reactivar producto
    @Modifying
    @Transactional
    @Query("UPDATE ProductEntity p SET p.estado = 'ACTIVO' WHERE p.idproducto = ?1")
    int activateProductById(Integer idProducto);
    
    @Modifying
    @Transactional
    @Query("UPDATE ProductEntity p SET p.estado = 'INACTIVO' WHERE p.idproducto = ?1")
    int deactivateProductById(Integer idProducto);
}
