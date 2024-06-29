package club_website.auth.Controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public Task addTask(@RequestBody Task t) {
		return taskService.addTask(t);
	}
	
	@GetMapping("/{id}")
	public Task getTaskById(@PathVariable Integer id){
		return taskService.getTaskById(id);
	}
	
	@GetMapping()
	public List<Task> getTasks(){
		return taskService.getTasks();
	}
	
	@DeleteMapping("/{id}")
	public void deleteTask(@PathVariable Integer id) {
		taskService.deleteTask(id);
	}
}
