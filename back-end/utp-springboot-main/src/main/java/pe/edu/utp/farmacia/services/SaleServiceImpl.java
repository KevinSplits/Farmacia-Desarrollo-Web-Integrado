package pe.edu.utp.farmacia.services;

import org.springframework.stereotype.Service;
import pe.edu.utp.farmacia.entity.SaleEntity;
import pe.edu.utp.farmacia.repositories.SaleRepository;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    
    private final SaleRepository saleRepository;
    
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }
    
    @Override
    public SaleEntity saveSale(SaleEntity sale) {
        return saleRepository.save(sale);
    }
    
    @Override
    public SaleEntity getSaleById(Integer id) {
        return saleRepository.findById(id).orElse(null);
    }
    
    @Override
    public List<SaleEntity> getAllSales() {
        return saleRepository.findAll();
    }
    
    @Override
    public void cancelSale(Integer id) {
        SaleEntity sale = saleRepository.findById(id).orElse(null);
        if (sale != null) {
            sale.setEstado("Anulado");
            saleRepository.save(sale);
        }
    }
}