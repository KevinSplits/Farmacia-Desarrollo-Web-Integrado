package pe.edu.utp.farmacia.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import pe.edu.utp.farmacia.entity.*;
import pe.edu.utp.farmacia.services.UserService;

@RestController
public class UserController {
	private UserService userService;
	
    public UserController(UserService userService) {
        this.userService = userService;
    }
	
    @GetMapping("/farmacia/userview")
	public ModelAndView UserViewList(){
    	ModelAndView model = new ModelAndView("user/user-list.html"); 
    	model.addObject("lstusuarios", userService.listAll());
		return model;
	}
    
    @GetMapping("/farmacia/useredit/{id}")
	public ModelAndView UserViewEdit(@PathVariable String id){
    	ModelAndView model = new ModelAndView("user/user-edit.html"); 
    	model.addObject("userEntity", userService.getById(Integer.valueOf(id)));
		return model;
	}
	
    @PostMapping("/farmacia/usersave")
	public ModelAndView UserSave(@ModelAttribute @Valid UserEntity userEntity){
		 userService.saveOrUpdate(userEntity);
		 return new ModelAndView("redirect:/farmacia/userview");
	}
    
    @GetMapping("/farmacia/usernew")
	public ModelAndView UserViewNew(){
    	ModelAndView model = new ModelAndView("user/user-edit.html"); 
    	model.addObject("userEntity", new UserEntity());
		return model;
	}
    
    @GetMapping("/farmacia/userdelete/{id}")
	public ModelAndView UserViewDelete(@PathVariable Integer id){
    	userService.nullable(id);
    	return new ModelAndView("redirect:/farmacia/userview");
	}
    
	@GetMapping("/farmacia/user")
	public List<UserEntity> UserList(){
		return userService.listAll();
	}
	
	@GetMapping("/farmacia/user/{id}")
	public UserEntity UserListByCodigo(@PathVariable String id){
		return userService.getById(Integer.valueOf(id));
	}
	
	@PostMapping("/farmacia/user")
	public UserEntity UserCreate(@RequestBody UserEntity input){
		return userService.saveOrUpdate(input);
	}
	
	@PutMapping("/farmacia/user/{id}")
	public UserEntity UserUpdate(@PathVariable("id") Integer id,@RequestBody UserEntity input){
		input.setIdusuario(id);
		return userService.saveOrUpdate(input);
	}
	
	@DeleteMapping("/farmacia/user/{id}")
	public String UserDelete(@PathVariable Integer id){
		userService.nullable(id);
		//userService.delete(id);
		return "Registro Anulado";
	}
}
