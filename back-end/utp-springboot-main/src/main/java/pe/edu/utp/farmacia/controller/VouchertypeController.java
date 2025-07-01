package pe.edu.utp.farmacia.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import pe.edu.utp.farmacia.entity.VouchertypeEntity;
import pe.edu.utp.farmacia.services.VouchertypeService;

@RestController
@RequestMapping("/farmacia/vouchertypes")
public class VouchertypeController {

    private VouchertypeService vouchertypeService;

    public VouchertypeController(VouchertypeService vouchertypeService) {
        this.vouchertypeService = vouchertypeService;
    }

    @GetMapping
    public List<VouchertypeEntity> getAllVouchertypes() {
        return vouchertypeService.listAll();
    }

    @GetMapping("/{id}")
    public VouchertypeEntity getVouchertypeById(@PathVariable Integer id) {
        return vouchertypeService.getById(id);
    }

    @PostMapping
    public VouchertypeEntity CreateVouchertype(@RequestBody VouchertypeEntity vouchertype) {
        return vouchertypeService.saveOrUpdate(vouchertype);
    }

    @PutMapping("/{id}")
    public VouchertypeEntity updateVouchertype(@PathVariable Integer id, @RequestBody VouchertypeEntity vouchertype) {
        vouchertype.setIdtipocomprobante(id);
        return vouchertypeService.saveOrUpdate(vouchertype);
    }

    @DeleteMapping("/{id}")
    public String deleteVouchertype(@PathVariable Integer id) {
        vouchertypeService.nullable(id);
        return "Registro Anulado";
    }
}
