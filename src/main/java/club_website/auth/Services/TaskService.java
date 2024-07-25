package club_website.auth.Services;

import java.util.List;


import org.springframework.stereotype.Service;

import club_website.auth.Models.Department;
import club_website.auth.Models.Task;

@Service
public interface TaskService {
	public Task addTask(Task t,String token);
	public List<Task> getTasks();
	public void deleteTask(Integer id,String token);
	public Task getTaskById(Integer id);
	public Task updateStatus(Task task);
	public List<Task> getTasksByDepartment(Department d);
	public Task updateTask(Task t,String token);
}
