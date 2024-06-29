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
import club_website.auth.Repositories.DepartmentRepo;
import club_website.auth.Repositories.EventRepo;
import club_website.auth.Services.EventService;


@Service
public class EventServiceImpl implements EventService{
	
	
	@Autowired
	private EventRepo eventRepo;
	
	@Autowired
	private DepartmentRepo departRepo;
	
	@Override
	public Event addEvent(Event e) {
		// TODO Auto-generated method stub
		System.out.println("e"+e);
		Set<Department> EventDeps = new HashSet<>();
        for (Department dep : e.getDepartments()) {
        	Department existingDep =departRepo.findById(dep.getId()).orElseThrow();
        	 EventDeps.add(existingDep);
        }
        e.setDepartments(EventDeps);
		return eventRepo.save(e);
	}

	@Override
	public List<Event> getEvents() {
		// TODO Auto-generated method stub
		List<Event> events=eventRepo.findAll();
		for(Event e:events) {
			e=updateStatus(e);
		}
		return events;
	}

	@Override
	public Event updateStatus(Event e) {
		// TODO Auto-generated method stub
		if(e.getDateEvent().before(new Date())) {
			e.setStatus(false);
			e=eventRepo.save(e);
		}
		return e;
	}

	@Override
	public void deleteEvent(Integer id) {
		// TODO Auto-generated method stub
		Event e=eventRepo.findById(id).get();
		eventRepo.delete(e);
	}

	@Override
	public Event getEventById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Event> e=eventRepo.findById(id);
		return e.isPresent()?e.get():null;
	}
	
	
	
	
}
