package pe.edu.utp.farmacia.services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import pe.edu.utp.farmacia.entity.UserEntity;
import pe.edu.utp.farmacia.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	@Override
	public List<UserEntity> listAll() {
		
        List<UserEntity> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            System.out.println("Adding user: " + user.getIdusuario());  // Para hacer un seguimiento de los ID
            users.add(user);
        });
        return users;
	}

	@Override
	public UserEntity getById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public UserEntity saveOrUpdate(UserEntity user) {
		userRepository.save(user);
        return user;
	}

	@Override
	public void delete(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public void nullable(Integer id) {
		userRepository.invalidateUserById(id);
		
	}
}
