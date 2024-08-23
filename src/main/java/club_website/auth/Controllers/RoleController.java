package club_website.auth.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club_website.auth.Models.Role;
import club_website.auth.Services.RoleService;

@RestController
@RequestMapping("/api/role")
@CrossOrigin("*")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	
	@PostMapping()
	public Role addRole(@RequestBody Role role) {
		return roleService.addRole(role);
	}
	
	@GetMapping
	public List<Role> getRoles(){
		return roleService.getRoles();
	}

}
