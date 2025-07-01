package pe.edu.utp.farmacia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pe.edu.utp.farmacia.repositories.ClientRepository;
import pe.edu.utp.farmacia.repositories.EmployeeRepository;
import pe.edu.utp.farmacia.repositories.ProductRepository;

@Controller
public class HomeController {
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private ProductRepository productRepository;

    @GetMapping({"/home", "/farmacia/"})
    public ModelAndView userViewList(){
        ModelAndView model = new ModelAndView("home");
        
        // Obtener información del usuario autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addObject("username", auth.getName());
        
        // Usamos los métodos específicos para contar solo los activos
        model.addObject("totalClientes", clientRepository.countActiveClients());
        model.addObject("totalEmpleados", employeeRepository.countActiveEmployees());
        model.addObject("totalProductos", productRepository.countActiveProducts());
        
        // Productos con stock bajo (menos de 10 unidades) y activos
        model.addObject("productosStockBajo", productRepository.findByStockLessThan(10));
        
        // Últimos 5 clientes registrados
        model.addObject("ultimosClientes", clientRepository.findTop5ByOrderByIdclienteDesc());
        
        return model;
    }
}