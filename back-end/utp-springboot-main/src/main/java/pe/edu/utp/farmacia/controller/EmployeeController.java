package pe.edu.utp.farmacia.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import pe.edu.utp.farmacia.entity.EmployeeEntity;
import pe.edu.utp.farmacia.services.EmployeeService;

@RestController
@RequestMapping("/farmacia/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployees() {
        return employeeService.listAll();
    }

    @GetMapping("/{id}")
    public EmployeeEntity getEmployeeById(@PathVariable Integer id) {
        return employeeService.getById(id);
    }

    @PostMapping
    public EmployeeEntity CreateEmployee(@RequestBody EmployeeEntity employee) {
        return employeeService.saveOrUpdate(employee);
    }

    @PutMapping("/{id}")
    public EmployeeEntity updateEmployee(@PathVariable Integer id, @RequestBody EmployeeEntity employee) {
        employee.setIdempleado(id);
        return employeeService.saveOrUpdate(employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeService.nullable(id);
        return "Registro Anulado";
    }
}
