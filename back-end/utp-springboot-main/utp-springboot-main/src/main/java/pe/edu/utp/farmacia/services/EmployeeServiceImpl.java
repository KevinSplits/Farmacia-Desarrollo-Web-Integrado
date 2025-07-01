package pe.edu.utp.farmacia.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import pe.edu.utp.farmacia.entity.EmployeeEntity;
import pe.edu.utp.farmacia.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeEntity> listAll() {
        List<EmployeeEntity> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees :: add);
        return employees;
    }

    @Override
    public EmployeeEntity getById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public EmployeeEntity saveOrUpdate(EmployeeEntity employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void nullable(Integer id) {
        employeeRepository.invalidateEmployeeById(id);
    }
}
