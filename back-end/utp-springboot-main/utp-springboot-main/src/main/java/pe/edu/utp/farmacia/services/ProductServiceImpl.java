package pe.edu.utp.farmacia.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pe.edu.utp.farmacia.entity.ProductEntity;
import pe.edu.utp.farmacia.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository productRepository;
	
	
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
	
	@Override
	public List<ProductEntity> listAll() {
        List<ProductEntity> products = new ArrayList<>();
        productRepository.findAll().forEach(product -> {
            System.out.println("Adding product: " + product.getIdproducto());  // Para hacer un seguimiento de los ID
            products.add(product);
        });
        return products;
	}

	@Override
	public ProductEntity getById(Integer id) {
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public ProductEntity saveOrUpdate(ProductEntity product) {
		productRepository.save(product);
        return product;
	}

	@Override
	public void delete(Integer id) {
		productRepository.deleteById(id);
	}

	@Override
	public void invalidate(Integer id) {
		productRepository.invalidateProductById(id);
	}
}
