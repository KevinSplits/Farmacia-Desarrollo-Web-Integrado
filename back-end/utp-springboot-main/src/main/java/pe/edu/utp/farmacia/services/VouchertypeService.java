package pe.edu.utp.farmacia.services;

import java.util.List;
import pe.edu.utp.farmacia.entity.VouchertypeEntity;

public interface VouchertypeService {

    List<VouchertypeEntity> listAll();

    VouchertypeEntity getById(Integer id);

    VouchertypeEntity saveOrUpdate(VouchertypeEntity vouchertype);

    void delete(Integer id);

    void nullable(Integer id);
}
