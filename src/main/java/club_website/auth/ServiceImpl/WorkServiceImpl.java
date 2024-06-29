package club_website.auth.ServiceImpl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club_website.auth.Models.Department;
import club_website.auth.Models.Task;
import club_website.auth.Models.User;
import club_website.auth.Models.Work;
import club_website.auth.Repositories.DepartmentRepo;
import club_website.auth.Repositories.TaskRepo;
import club_website.auth.Repositories.WorkRepo;
import club_website.auth.Services.TaskService;
import club_website.auth.Services.UserService;
import club_website.auth.Services.WorkService;
@Service
public class WorkServiceImpl implements WorkService{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskRepo taskRepo;
	
	@Autowired
	private WorkRepo workRepo;
	
	@Autowired
	private DepartmentRepo departmentRepo;

	
	@Override
	public List<Work> getWorksByTask(Task task) {
        return workRepo.findByTask(task);
    }
	
	@Override
	public List<Work> addDepos(Task task) throws Exception{
		// TODO Auto-generated method stub
        try {
        		System.out.println("here");
        		List<User> users=userService.getUsersByDepart(task.getDepartment());
        		System.out.println("users"+users);
        		List<Work> works=new ArrayList<Work>();
    			for(User u:users) {
        				Work work=Work.builder().user(u).task(task)
                				.build();
                		works.add(workRepo.save(work));
    			}
        		System.out.println("depos"+works);
        		return works;
        }
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
		
        
	}

	@Override
	public List<Work> getAllWorks() {
		// TODO Auto-generated method stub
		return workRepo.findAll();
	}

	@Override
	public Work getWorkById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Work> work=workRepo.findById(id);
		return work.isPresent()?work.get():null;
	}

	@Override
	public Work submitWork(Work w) {
		// TODO Auto-generated method stub
		Work work=getWorkById(w.getId());
		if(work!=null && work.isStatus()==true) {
			work.setUrl(w.getUrl());
			work.setStatus(false);
			work=workRepo.save(work);
		}
		return work;
	}

	@Override
	public Work updateScore(Work w) {
		// TODO Auto-generated method stub
		Work work=getWorkById(w.getId());
		if(work!=null) {
			work.setScore(w.getScore());
			work=workRepo.save(work);
		}
		return work;
	}
	
	
	
	
}
	

