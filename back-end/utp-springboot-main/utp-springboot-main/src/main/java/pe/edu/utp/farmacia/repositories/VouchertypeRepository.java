package pe.edu.utp.farmacia.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;
import pe.edu.utp.farmacia.entity.VouchertypeEntity;


public interface VouchertypeRepository extends CrudRepository<VouchertypeEntity, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE VouchertypeEntity v SET v.estado = 'Inactivo' WHERE v.idtipocomprobante = ?1")
    int invalidateVouchertypeById(Integer id);
}
