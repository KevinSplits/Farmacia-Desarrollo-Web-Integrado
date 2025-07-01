/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.utp.farmacia.controller;

import java.util.HashMap;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.farmacia.entity.SaleEntity;
import pe.edu.utp.farmacia.services.SaleService;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/farmacia/ventas/api")
public class SaleRestController {

    private final SaleService saleService;

    public SaleRestController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/listar")
    public List<SaleEntity> listarVentas() {
        return saleService.getAllSales();
    }

    @GetMapping("/anular/{id}")
    public void anularVenta(@PathVariable Integer id) {
        saleService.cancelSale(id);
    }

  @PostMapping("/guardar")
public ResponseEntity<Map<String, Object>> guardarVenta(@RequestBody SaleEntity venta) {
    saleService.saveSale(venta);
    Map<String, Object> response = new HashMap<>();
    response.put("mensaje", "Venta guardada");
    response.put("success", true);
    return ResponseEntity.ok().body(response);
}
}