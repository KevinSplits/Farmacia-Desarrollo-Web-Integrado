package pe.edu.utp.farmacia.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import pe.edu.utp.farmacia.entity.VouchertypeEntity;
import pe.edu.utp.farmacia.repositories.VouchertypeRepository;

@Service
public class VouchertypeServiceImpl implements VouchertypeService {

    private VouchertypeRepository vouchertypeRepository;

    public VouchertypeServiceImpl(VouchertypeRepository vouchertypeRepository) {
        this.vouchertypeRepository = vouchertypeRepository;
    }

    @Override
    public List<VouchertypeEntity> listAll() {
        List<VouchertypeEntity> vouchertypes = new ArrayList<>();
        vouchertypeRepository.findAll().forEach(vouchertypes::add);
        return vouchertypes;
    }

    @Override
    public VouchertypeEntity getById(Integer id) {
        return vouchertypeRepository.findById(id).orElse(null);
    }

    @Override
    public VouchertypeEntity saveOrUpdate(VouchertypeEntity vouchertype) {
        vouchertypeRepository.save(vouchertype);
        return vouchertype;
    }

    @Override
    public void delete(Integer id) {
        vouchertypeRepository.deleteById(id);
    }

    @Override
    public void nullable(Integer id) {
        vouchertypeRepository.invalidateVouchertypeById(id);
    }
}

