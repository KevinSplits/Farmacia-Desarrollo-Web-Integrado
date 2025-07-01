package pe.edu.utp.farmacia.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;
import pe.edu.utp.farmacia.entity.ProductEntity;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE ProductEntity p SET p.estado = 'Inactivo' WHERE p.idproducto = ?1")
    int invalidateProductById(Integer idProducto);
}
