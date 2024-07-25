package club_website.auth.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club_website.auth.Models.User;
import club_website.auth.Models.Work;
import club_website.auth.Services.WorkService;

@CrossOrigin("*")
@RequestMapping("/api/work")
@RestController
public class WorkController {
	
	@Autowired
	private WorkService workService;
	
	
	
	@GetMapping("/{id}")
	public Work getWorkById(@PathVariable Integer id){
		return workService.getWorkById(id);
	}
	
	//,@RequestHeader("Authorization") String token
	@GetMapping("member/{id}")
	public List<Work> getWorksByMember(@PathVariable Integer id){
		return workService.getWorksByMember(id);
	}
	
	
	@GetMapping
	public List<Work> getAllWorks(){
		return workService.getAllWorks();
	}
	
	@PutMapping("note/{id}")
	public Work updateNote(@PathVariable Integer id,@RequestBody double note,@RequestHeader("Authorization") String token) {
		return workService.updateNote(id,note,token.substring(7));
	}
	
	@PutMapping("submit/{id}")
	public Work submitWork(@PathVariable Integer id,@RequestBody String url,@RequestHeader("Authorization") String token) {
		return workService.submitWork(id,url,token.substring(7));
	}
	
}
