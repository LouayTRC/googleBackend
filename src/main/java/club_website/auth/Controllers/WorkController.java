package club_website.auth.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club_website.auth.Config.JwtService;
import club_website.auth.Models.User;
import club_website.auth.Models.Work;
import club_website.auth.Repositories.UserRepo;
import club_website.auth.Services.WorkService;

@CrossOrigin("*")
@RequestMapping("/api/work")
@RestController
public class WorkController {
	
	@Autowired
	private WorkService workService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserRepo userRepo;
	
	
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('MEMBER')")
	public Work getWorkById(@PathVariable Integer id){
		return workService.getWorkById(id);
	}
	
	//,@RequestHeader("Authorization") String token
	@GetMapping("/member")
	@PreAuthorize("hasAuthority('MEMBER')")
	public List<Work> getWorksByMember(@RequestHeader("Authorization") String token){
		String username=jwtService.extractUsername(token.substring(7));
		User user=userRepo.findByUsername(username).get();
		
		return workService.getWorksByMember(user.getMember().getMember_id());
	}
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Work> getAllWorks(){
		return workService.getAllWorks();
	}
	
	@PutMapping("note/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Work updateNote(@PathVariable Integer id,@RequestBody double note,@RequestHeader("Authorization") String token) {
		return workService.updateNote(id,note,token.substring(7));
	}
	
	@PutMapping("submit/{id}")
	@PreAuthorize("hasAuthority('MEMBER')")
	public Work submitWork(@PathVariable Integer id,@RequestBody String url,@RequestHeader("Authorization") String token) {
		return workService.submitWork(id,url,token.substring(7));
	}
	
}
