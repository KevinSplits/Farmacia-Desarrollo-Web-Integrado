package pe.edu.utp.farmacia.services;

import java.util.List;
import pe.edu.utp.farmacia.entity.ProductEntity;

public interface ProductService {
    List<ProductEntity> listAll();
    List<ProductEntity> listAvailable(); // Nuevo método para productos disponibles
    List<ProductEntity> listLowStock(int threshold); // Nuevo método para stock bajo
    List<ProductEntity> searchAvailable(String searchTerm); // Nuevo método para búsqueda
    List<ProductEntity> listInactive();
    ProductEntity getById(Integer id);
    ProductEntity saveOrUpdate(ProductEntity product);
    void delete(Integer id);
    void invalidate(Integer id);
    void activate(Integer id);
}