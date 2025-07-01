package pe.edu.utp.farmacia.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.farmacia.entity.ClientEntity;
import pe.edu.utp.farmacia.services.ClientService;


@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/farmacia/clientes")
public class ClientViewController {

    private final ClientService clientService;

    public ClientViewController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String listClients(Model model) {
        List<ClientEntity> clients = clientService.listAll();
        model.addAttribute("clients", clients);
        return "clientes";
    }

    @GetMapping("/agregar")
    public String showAddForm(Model model) {
        model.addAttribute("client", new ClientEntity());
        return "agregar-cliente";
    }

    @PostMapping("/guardar")
    public String saveClient(@ModelAttribute ClientEntity client) {
        // Establecer estado como Activo por defecto si es nuevo cliente
        if(client.getIdcliente() == null) {
            client.setEstado("Activo");
        }
        clientService.saveOrUpdate(client);
        return "redirect:/farmacia/clientes";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        ClientEntity client = clientService.getById(id);
        if(client == null) {
            return "redirect:/farmacia/clientes";
        }
        model.addAttribute("client", client);
        return "editar-cliente";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteClient(@PathVariable Integer id) {
        clientService.nullable(id);
        return "redirect:/farmacia/clientes";
    }
}