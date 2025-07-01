package pe.edu.utp.farmacia.services;

import pe.edu.utp.farmacia.entity.SaleEntity;
import java.util.List;

public interface SaleService {
    SaleEntity saveSale(SaleEntity sale);
    SaleEntity getSaleById(Integer id);
    List<SaleEntity> getAllSales();
    void cancelSale(Integer id);
}