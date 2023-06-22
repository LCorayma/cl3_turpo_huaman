package cibertec.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cibertec.edu.models.Producto;
import cibertec.edu.repositories.ProductoDao;

@Controller
public class MainController {
	
	@Autowired
	private ProductoDao productoRepository;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/registro")
	public String registro(@ModelAttribute("producto") Producto producto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			return "registro";
		} else {
			return "denegado";
		}
	}
	
	@PostMapping("/registro")
	public String registrarEstudiante(@Validated @ModelAttribute("producto") Producto producto, BindingResult bindingResult) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			if (bindingResult.hasErrors()) {
				return "registro";
			}
			
			productoRepository.save(producto);
			
			return "redirect:/";
		} else {
			return "denegado";
		}
	}

}
