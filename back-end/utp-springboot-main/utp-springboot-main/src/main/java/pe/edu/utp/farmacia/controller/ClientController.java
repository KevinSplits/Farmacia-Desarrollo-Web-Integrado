package pe.edu.utp.farmacia.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import pe.edu.utp.farmacia.entity.ClientEntity;
import pe.edu.utp.farmacia.services.ClientService;

@RestController
@RequestMapping("/farmacia/clients")
public class ClientController {

    private ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientEntity> getAllClients() {
        return clientService.listAll();
    }

    @GetMapping("/{id}")
    public ClientEntity getClientById(@PathVariable Integer id) {
        return clientService.getById(id);
    }

    @PostMapping
    public ClientEntity createClient(@RequestBody ClientEntity client) {
        return clientService.saveOrUpdate(client);
    }

    @PutMapping("/{id}")
    public ClientEntity updateClient(@PathVariable Integer id, @RequestBody ClientEntity client) {
        client.setIdcliente(id);
        return clientService.saveOrUpdate(client);
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable Integer id) {
        clientService.nullable(id);
        return "Registro Anulado";
    }
}
