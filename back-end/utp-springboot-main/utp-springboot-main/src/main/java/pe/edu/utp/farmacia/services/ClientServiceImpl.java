package pe.edu.utp.farmacia.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import pe.edu.utp.farmacia.entity.ClientEntity;
import pe.edu.utp.farmacia.repositories.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {
    
    private ClientRepository clientRepository;
    
  
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    
    @Override
    public List<ClientEntity> listAll() {
        List<ClientEntity> clients = new ArrayList<>();
        clientRepository.findAll().forEach(clients::add);
        return clients;
    }

    @Override
    public ClientEntity getById(Integer id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public ClientEntity saveOrUpdate(ClientEntity client) {
        clientRepository.save(client);
        return client;
    }

    @Override
    public void delete(Integer id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void nullable(Integer id) {
        clientRepository.invalidateClientById(id);
    }
}
