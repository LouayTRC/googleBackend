package club_website.auth.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club_website.auth.Models.Admin;
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
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Member> getAllMembers(){
		return userService.getAllMembers();
	}
	
	@GetMapping("leaderboard")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MEMBER')")
	public List<Member> getLeaderBoard(){
		return userService.getLeaderBoard();
	}
	
	@GetMapping("admin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Admin> getAllAdmins(){
		return userService.getAllAdmins();
	}
	
	@GetMapping()
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
//	@DeleteMapping("/{id}")
//	public void deleteUser(@PathVariable Integer id,@RequestHeader("Authorization") String token) {
//		userService.deleteUser(id);
//	}
	
	@PutMapping("/status/{id}")
	@PreAuthorize("hasAuthority('SUPER ADMIN')")
	public ResponseEntity activateAccount(@PathVariable Integer id,@RequestBody boolean status) {
		return userService.activateUser(id,status);
	}
}
