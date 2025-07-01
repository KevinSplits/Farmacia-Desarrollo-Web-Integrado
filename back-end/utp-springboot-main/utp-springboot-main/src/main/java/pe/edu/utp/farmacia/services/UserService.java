package pe.edu.utp.farmacia.services;

import java.util.List;
import pe.edu.utp.farmacia.entity.UserEntity;

public interface UserService {
	
	List<UserEntity> listAll();

	UserEntity getById(Integer id);

	UserEntity saveOrUpdate(UserEntity user);

    void delete(Integer id);
    
    void nullable(Integer id);
}
