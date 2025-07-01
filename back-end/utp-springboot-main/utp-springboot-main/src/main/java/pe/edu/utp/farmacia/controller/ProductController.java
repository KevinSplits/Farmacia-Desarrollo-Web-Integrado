package pe.edu.utp.farmacia.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import pe.edu.utp.farmacia.entity.ProductEntity;
import pe.edu.utp.farmacia.services.ProductService;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping("/farmacia/product")
    public List<ProductEntity> productList() {
        return productService.listAll();
    }

    @GetMapping("/farmacia/product/{id}")
    public ProductEntity productListById(@PathVariable String id) {
        return productService.getById(Integer.valueOf(id));
    }

    @PostMapping("/farmacia/product")
    public ProductEntity productCreate(@RequestBody ProductEntity input) {
        return productService.saveOrUpdate(input);
    }

    @PutMapping("/farmacia/product/{id}")
    public ProductEntity productUpdate(@PathVariable("id") Integer id, @RequestBody ProductEntity input) {
        input.setIdproducto(id);
        return productService.saveOrUpdate(input);
    }

    @DeleteMapping("/farmacia/product/{id}")
    public String productDelete(@PathVariable Integer id) {
        productService.invalidate(id);
        return "Producto Anulado";
    }
}
