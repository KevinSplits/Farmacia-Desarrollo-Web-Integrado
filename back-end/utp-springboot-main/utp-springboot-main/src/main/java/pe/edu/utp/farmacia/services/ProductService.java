package pe.edu.utp.farmacia.services;

import java.util.List;
import pe.edu.utp.farmacia.entity.ProductEntity;

public interface ProductService {

    List<ProductEntity> listAll();

    ProductEntity getById(Integer id);

    ProductEntity saveOrUpdate(ProductEntity product);

    void delete(Integer id);
    
    void invalidate(Integer id);
}
