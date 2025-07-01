package pe.edu.utp.farmacia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
	
	@GetMapping("/farmacia/")
	public ModelAndView UserViewList(){
    	ModelAndView model = new ModelAndView("home.html");
		return model;
	}
}