package club_website.auth.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping
	public List<Work> getAllWorks(){
		return workService.getAllWorks();
	}
	
	@PutMapping("score")
	public Work updateScore(@RequestBody Work w) {
		return workService.updateScore(w);
	}
	
	@PutMapping("work")
	public Work submitWork(@RequestBody Work w) {
		return workService.submitWork(w);
	}
	
}
