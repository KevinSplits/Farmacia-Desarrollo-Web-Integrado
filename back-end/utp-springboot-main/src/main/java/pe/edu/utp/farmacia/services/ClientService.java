package pe.edu.utp.farmacia.services;

import java.util.List;
import pe.edu.utp.farmacia.entity.ClientEntity;

public interface ClientService {
    
    List<ClientEntity> listAll();

    ClientEntity getById(Integer id);

    ClientEntity saveOrUpdate(ClientEntity client);

    void delete(Integer id);

    void nullable(Integer id);
}
