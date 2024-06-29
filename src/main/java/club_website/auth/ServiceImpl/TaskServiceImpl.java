package club_website.auth.ServiceImpl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club_website.auth.Models.Department;
import club_website.auth.Models.Event;
import club_website.auth.Models.Task;
import club_website.auth.Models.Work;
import club_website.auth.Repositories.DepartmentRepo;
import club_website.auth.Repositories.TaskRepo;
import club_website.auth.Services.TaskService;
import club_website.auth.Services.WorkService;
import jakarta.transaction.Transactional;
@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskRepo taskRepo;
	
	@Autowired
	private WorkService workService;
	
	@Autowired
	private DepartmentRepo DepartRepo;
	
	@Override
	public Task addTask(Task t) {
		// TODO Auto-generated method stub  	
	    	try {
	    		Department dep=DepartRepo.findById(t.getDepartment().getId()).get();
	    		t.setDepartment(dep);
	    	    t.setDateCreation(new Date());
	    	    Task res=taskRepo.save(t);
				workService.addDepos(t);
				return res;

	    	}
	    	catch (Exception e) {
				// TODO: handle exception
	    		System.out.println("aaaa");
	    		return null;
			}
	    
	}

	@Override
	public List<Task> getTasks() {
		// TODO Auto-generated method stub
		List<Task> tasks=taskRepo.findAll();
		for(Task t:tasks) {
			if(t.getDdl()!=null) {
				t=updateStatus(t);
			}
		}
		return tasks;
	}

	@Override
	public void deleteTask(Integer id) {
		// TODO Auto-generated method stub
		try {
			Optional<Task> t=taskRepo.findById(id);
			if(t.isPresent()) {
				taskRepo.delete(t.get());
				System.out.println("suppression effectue");
			}
			else {
				System.out.println("task introuvable");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("suppression impo");
		}
	}

	@Override
	public Task getTaskById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Task> t=taskRepo.findById(id);
		return t.isPresent()?t.get():null;
	}

	@Override
	public Task updateStatus(Task task) {
		// TODO Auto-generated method stub
		if(task.getDdl().before(new Date())) {
			task.setStatus(false);
			task=taskRepo.save(task);
		}
		return task;
	}
	
	
	
}
