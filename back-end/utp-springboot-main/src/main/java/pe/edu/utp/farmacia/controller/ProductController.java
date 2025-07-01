
package pe.edu.utp.farmacia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.farmacia.entity.ProductEntity;
import pe.edu.utp.farmacia.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/farmacia/productos")
public class ProductController {

    // Endpoint REST para obtener producto por ID (JSON)
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/editar/{id}")
    @ResponseBody
    public ProductEntity getProductByIdApi(@PathVariable Integer id) {
        return productService.getById(id);
    }

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    // Vista del inventario
    @GetMapping("/inventario")
    public String showInventory(Model model,
                                @RequestParam(required = false) String search) {
        List<ProductEntity> productos;
        List<ProductEntity> productosInactivos;

        if (search != null && !search.isEmpty()) {
            productos = productService.searchAvailable(search);
            // podrías hacer una búsqueda inactiva también si lo deseas
            productosInactivos = productService.listInactive(); // opcional: filtrar con search
            model.addAttribute("searchTerm", search);
        } else {
            productos = productService.listAvailable();
            productosInactivos = productService.listInactive();
        }

        model.addAttribute("productos", productos);
        model.addAttribute("inactivos", productosInactivos); // nuevo
        return "inventario";
    }
    
    // Vista para productos con stock bajo
    @GetMapping("/inventario/bajo-stock")
    public String showLowStockInventory(Model model,
                                      @RequestParam(defaultValue = "10") int threshold) {
        List<ProductEntity> productos = productService.listLowStock(threshold);
        model.addAttribute("productos", productos);
        model.addAttribute("threshold", threshold);
        return "inventario-bajo-stock";
    }
    
    // Vista para editar producto
    @GetMapping("/editar/{id}")
    public String editProductForm(@PathVariable Integer id, Model model) {
        ProductEntity producto = productService.getById(id);
        model.addAttribute("producto", producto);
        return "editar-producto";
    }
    
    // Procesar actualización de producto
    @PostMapping("/actualizar")
    public String updateProduct(@ModelAttribute ProductEntity producto) {
        productService.saveOrUpdate(producto);
        return "redirect:/farmacia/productos/inventario";
    }
    
    @GetMapping("/activar/{id}")
    public String activateProduct(@PathVariable Integer id) {
        productService.activate(id);
        return "redirect:/farmacia/productos/inventario";
    }
    
    @GetMapping("/invalidate/{id}")
    public String invalidateProduct(@PathVariable Integer id) {
        productService.invalidate(id); // Cambia el estado a "INACTIVO"
        return "redirect:/farmacia/productos/inventario";
    }

}