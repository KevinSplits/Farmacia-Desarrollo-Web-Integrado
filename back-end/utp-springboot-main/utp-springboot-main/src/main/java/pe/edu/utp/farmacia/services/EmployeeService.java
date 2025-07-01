package pe.edu.utp.farmacia.services;

import java.util.List;
import pe.edu.utp.farmacia.entity.EmployeeEntity;


public interface EmployeeService {

    List<EmployeeEntity> listAll();

    EmployeeEntity getById(Integer id);

    EmployeeEntity saveOrUpdate(EmployeeEntity employee);

    void delete(Integer id);

    void nullable(Integer id);
}
