package pe.edu.utp.farmacia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.farmacia.entity.EmployeeEntity;
import pe.edu.utp.farmacia.services.EmployeeService;

@Controller
@RequestMapping("/farmacia/empleados")
public class EmployeeViewController {

    private final EmployeeService employeeService;

    public EmployeeViewController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.listAll());
        return "empleados";
    }

    @GetMapping("/agregar")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new EmployeeEntity());
        return "agregar-empleado";
    }

    @PostMapping("/guardar")
    public String saveEmployee(@ModelAttribute EmployeeEntity employee) {
        if(employee.getIdempleado() == null) {
            employee.setEstado("Activo");
        }
        employeeService.saveOrUpdate(employee);
        return "redirect:/farmacia/empleados";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        EmployeeEntity employee = employeeService.getById(id);
        if(employee == null) {
            return "redirect:/farmacia/empleados";
        }
        model.addAttribute("employee", employee);
        return "editar-empleado";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeService.nullable(id);
        return "redirect:/farmacia/empleados";
    }
}