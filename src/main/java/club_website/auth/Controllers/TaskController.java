package club_website.auth.Controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club_website.auth.Models.Department;
import club_website.auth.Models.Task;
import club_website.auth.Services.TaskService;

@CrossOrigin("*")
@RequestMapping("/api/task")
@RestController
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@PostMapping()
	@PreAuthorize("hasAuthority('ADMIN')")
	public Task addTask(@RequestBody Task t,@RequestHeader("Authorization") String token) {
		return taskService.addTask(t,token.substring(7));
	}
	
//	@PostMapping("/dep")
//	public List<Task> getTasksByDepartment(@RequestBody Department d){
//		return taskService.getTasksByDepartment(d);
//	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Task getTaskById(@PathVariable Integer id){
		return taskService.getTaskById(id);
	}
	
	@GetMapping()
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Task> getTasks(){
		return taskService.getTasks();
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteTask(@PathVariable Integer id,@RequestHeader("Authorization") String token) {
		taskService.deleteTask(id,token.substring(7));
	}
	
	@PutMapping()
	@PreAuthorize("hasAuthority('ADMIN')")
	public Task updateTask(@RequestBody Task t,@RequestHeader("Authorization") String token) {
		return taskService.updateTask(t,token.substring(7));
	}
}
