package pe.edu.utp.farmacia.services;

import java.util.List;
import org.springframework.stereotype.Service;
import pe.edu.utp.farmacia.entity.ProductEntity;
import pe.edu.utp.farmacia.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;
    
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @Override
    public List<ProductEntity> listAll() {
        return (List<ProductEntity>) productRepository.findAll();
    }
    
    @Override
    public List<ProductEntity> listAvailable() {
        return productRepository.findAvailableProducts();
    }
    
    @Override
    public List<ProductEntity> listLowStock(int threshold) {
        return productRepository.findByStockLessThan(threshold);
    }
    
    @Override
    public List<ProductEntity> searchAvailable(String searchTerm) {
        return productRepository.searchAvailableProducts(searchTerm);
    }
    
    @Override
    public ProductEntity getById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }
    
    @Override
    public ProductEntity saveOrUpdate(ProductEntity product) {
        return productRepository.save(product);
    }
    
    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
    
    @Override
    public void invalidate(Integer id) {
        productRepository.deactivateProductById(id);
    }
    
    @Override
    public List<ProductEntity> listInactive() {
        return productRepository.findInactiveProducts();
    }

    @Override
    public void activate(Integer id) {
        productRepository.activateProductById(id);
    }

}