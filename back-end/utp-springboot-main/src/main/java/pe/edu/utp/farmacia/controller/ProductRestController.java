/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.utp.farmacia.controller;

import org.springframework.web.bind.annotation.*;
import pe.edu.utp.farmacia.entity.ProductEntity;
import pe.edu.utp.farmacia.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/farmacia/productos/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/inventario")
    public List<ProductEntity> getProductosActivos() {
        return productService.listAvailable();
    }

    @GetMapping("/inactivos")
    public List<ProductEntity> getProductosInactivos() {
        return productService.listInactive();
    }

    @GetMapping("/inventario/bajo-stock")
    public List<ProductEntity> getProductosStockBajo(@RequestParam(defaultValue = "10") int threshold) {
        return productService.listLowStock(threshold);
    }
}