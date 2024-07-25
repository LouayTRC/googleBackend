package club_website.auth.ServiceImpl;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club_website.auth.Config.JwtService;
import club_website.auth.Models.Department;
import club_website.auth.Models.Event;
import club_website.auth.Models.Task;
import club_website.auth.Models.User;
import club_website.auth.Models.Work;
import club_website.auth.Repositories.DepartmentRepo;
import club_website.auth.Repositories.TaskRepo;
import club_website.auth.Repositories.UserRepo;
import club_website.auth.Services.TaskService;
import club_website.auth.Services.WorkService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskRepo taskRepo;
	
	@Autowired
	private WorkService workService;
	
	@Autowired
	private DepartmentRepo DepartRepo;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Task addTask(Task t,String token) {
		// TODO Auto-generated method stub  	
	    	try {
	    		
	    		String username=jwtService.extractUsername(token);
				User u=userRepo.findByUsername(username).get();
				
				
	    		System.out.println("task"+t);
	    		Department dep=DepartRepo.findById(t.getDepartment().getId()).get();
	    		System.out.println("dep"+dep);
	    		t.setDepartment(dep);
	    	    t.setDateCreation(new Date());
	    	    
	    	    t.setCratedAt(LocalDate.now());
	    	    t.setCreatedBy(u.getAdmin());
	    	    
	    	    
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
		
		enableDeletedFilter(false);
		List<Task> tasks=taskRepo.findAll();
		for(Task t:tasks) {
			if(t.getDdl()!=null) {
				t=updateStatus(t);
			}
		}
        disableDeletedFilter();
        
        return tasks;
	}

	@Override
	public void deleteTask(Integer id,String token) {
		// TODO Auto-generated method stub
		try {
			String username=jwtService.extractUsername(token);
			User u=userRepo.findByUsername(username).get();
			
			Task task = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
			
			task.setDeleted(true);
			task.setDeletedAt(LocalDate.now());
			task.setDeletedBy(u.getAdmin());
			taskRepo.save(task);
			System.out.println("suppression effectue");	
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

	@Override
	public List<Task> getTasksByDepartment(Department d) {
		// TODO Auto-generated method stub
		return taskRepo.findByDepartment(d);
	}

	@Override
	public Task updateTask(Task task,String token) {
		// TODO Auto-generated method stub
		try {
			String username=jwtService.extractUsername(token);
			User u=userRepo.findByUsername(username).get();
			
			Task t=taskRepo.findById(task.getId()).get();
			t.setDdl(task.getDdl());
			t.setDescription(task.getDescription());
			t.setTitle(task.getTitle());
			
			t.setUpdatedAt(LocalDate.now());
			t.setUpdatedBy(u.getAdmin());
			
			return taskRepo.save(t);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("update impo");
			return null;
		}
		
	}
	
	private void enableDeletedFilter(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedTasksFilter");
        filter.setParameter("isDeleted", isDeleted);
    }

    private void disableDeletedFilter() {
        Session session = entityManager.unwrap(Session.class);
        session.disableFilter("deletedTasksFilter");
    }
	
}
