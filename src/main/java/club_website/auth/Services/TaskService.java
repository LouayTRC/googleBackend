package club_website.auth.Services;

import java.util.List;


import org.springframework.stereotype.Service;


import club_website.auth.Models.Task;

@Service
public interface TaskService {
	public Task addTask(Task t);
	public List<Task> getTasks();
	public void deleteTask(Integer id);
	public Task getTaskById(Integer id);
	public Task updateStatus(Task task);
}
