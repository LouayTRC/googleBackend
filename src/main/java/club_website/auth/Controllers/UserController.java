package club_website.auth.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club_website.auth.Models.Member;
import club_website.auth.Models.User;
import club_website.auth.Services.UserService;

@RequestMapping("/api/user")
@CrossOrigin("*")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("member")
	public List<Member> getAllMembers(){
		return userService.getAllMembers();
	}
	
	@GetMapping("admin")
	public List<User> getAllAdmins(){
		return userService.getAllAdmins();
	}
	
	@GetMapping()
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
	}
}
