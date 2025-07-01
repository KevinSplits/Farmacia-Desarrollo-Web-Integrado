package pe.edu.utp.farmacia.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.farmacia.entity.*;
import pe.edu.utp.farmacia.services.*;

import java.util.Date;
import java.util.List;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/farmacia/ventas")
public class SaleViewController {
    
    private final SaleService saleService;
    private final ClientService clientService;
    private final EmployeeService employeeService;
    private final ProductService productService;
    private final VouchertypeService voucherTypeService;
    
    public SaleViewController(SaleService saleService, ClientService clientService, 
                            EmployeeService employeeService, ProductService productService,
                            VouchertypeService voucherTypeService) {
        this.saleService = saleService;
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.productService = productService;
        this.voucherTypeService = voucherTypeService;
    }
    
    @GetMapping
    public String listSales(Model model) {
        model.addAttribute("sales", saleService.getAllSales());
        return "ventas";
    }
    
    @GetMapping("/nueva")
    public String showNewSaleForm(Model model) {
        SaleEntity sale = new SaleEntity();
        sale.setFecha(new Date());
        sale.setDetalles(new ArrayList<>()); // Inicializa la lista de detalles

        List<ProductEntity> availableProducts = productService.listAvailable();

        model.addAttribute("sale", sale);
        model.addAttribute("clients", clientService.listAll());
        model.addAttribute("employees", employeeService.listAll());
        model.addAttribute("productos", availableProducts);
        model.addAttribute("voucherTypes", voucherTypeService.listAll());

        return "nueva-venta";
    }
    
    @PostMapping("/guardar")
    public String saveSale(@ModelAttribute SaleEntity sale, BindingResult result) {
        if (result.hasErrors()) {
            return "nueva-venta";
        }

        // Asigna la venta a cada detalle
        sale.getDetalles().forEach(detalle -> detalle.setVenta(sale));

        calculateTotals(sale);
        saleService.saveSale(sale);
        return "redirect:/farmacia/ventas";
    }
    
    @GetMapping("/anular/{id}")
    public String cancelSale(@PathVariable Integer id) {
        saleService.cancelSale(id);
        return "redirect:/farmacia/ventas";
    }
    
    private void calculateTotals(SaleEntity sale) {
        BigDecimal subtotal = BigDecimal.ZERO;
        
        for (SaleDetailEntity detail : sale.getDetalles()) {
            BigDecimal precio = detail.getPrecioUnitario();
            int cantidad = detail.getCantidad();
            BigDecimal descuento = detail.getDescuento() != null ? detail.getDescuento() : BigDecimal.ZERO;
            
            BigDecimal subtotalLinea = precio.multiply(BigDecimal.valueOf(cantidad)).subtract(descuento);
            detail.setSubtotal(subtotalLinea);
            
            subtotal = subtotal.add(subtotalLinea);
        }
        
        sale.setSubtotal(subtotal);
        
        // Aplicar descuento general si existe
        BigDecimal descuentoGeneral = sale.getDescuento() != null ? sale.getDescuento() : BigDecimal.ZERO;
        BigDecimal baseImponible = subtotal.subtract(descuentoGeneral);
        
        // Calcular IGV (18%)
        BigDecimal igv = baseImponible.multiply(new BigDecimal("0.18"));
        sale.setIgv(igv);
        
        // Calcular total
        sale.setTotal(baseImponible.add(igv));
    }
}