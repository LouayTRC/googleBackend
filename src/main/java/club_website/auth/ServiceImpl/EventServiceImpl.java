package club_website.auth.ServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import club_website.auth.Config.JwtService;
import club_website.auth.Models.Department;
import club_website.auth.Models.Event;
import club_website.auth.Models.MemberEvent;
import club_website.auth.Models.Task;
import club_website.auth.Models.User;
import club_website.auth.Repositories.DepartmentRepo;
import club_website.auth.Repositories.EventRepo;
import club_website.auth.Repositories.UserRepo;
import club_website.auth.Services.EventService;
import club_website.auth.Services.MemberEventService;
import jakarta.persistence.EntityManager;



@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EventRepo eventRepo;
	
	@Autowired
	private DepartmentRepo departRepo;
	
	@Autowired
    private EntityManager entityManager;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private MemberEventService presenceService;
	
	@Override
	public Event addEvent(Event e,String token) {
		// TODO Auto-generated method stub
		try {
			String username=jwtService.extractUsername(token);
			User u=userRepo.findByUsername(username).get();
			
			Set<Department> EventDeps = new HashSet<>();
	        for (Department dep : e.getDepartments()) {
	        	Department existingDep =departRepo.findById(dep.getId()).orElseThrow();
	        	 EventDeps.add(existingDep);
	        }
	        e.setDepartments(EventDeps);
	        e.setCreatedAt(LocalDateTime.now());
	        e.setCreatedBy(u.getAdmin());
	        e=eventRepo.save(e);
	        List<MemberEvent> memberEvents=presenceService.addPresence(e);
	        e.setPresenceList(memberEvents);
			return e;
			
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println(e2.getMessage());
			return null;
		}
		
	}

	@Override
	public List<Event> getEvents() {
		// TODO Auto-generated method stub
		enableDeletedFilter(false);
        List<Event> events = eventRepo.findAll();
        disableDeletedFilter();
        return events;
	}

	@Override
	public Event updateStatus(Event e) {
		// TODO Auto-generated method stub
		if(e.getDateEvent().isBefore(LocalDateTime.now())) {
			e.setStatus(false);
			e=eventRepo.save(e);
		}
		return e;
	}

	@Override
	public boolean deleteEvent(Integer id,String token) {
		// TODO Auto-generated method stub
		try {
			String username=jwtService.extractUsername(token);
			User u=userRepo.findByUsername(username).get();
			
			Event event = eventRepo.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
	        
			event.setDeleted(true);
			event.setDeletedAt(LocalDateTime.now());
			event.setDeletedBy(u.getAdmin());
			eventRepo.save(event);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
		
	}

	@Override
	public Event getEventById(Integer id) {
		// TODO Auto-generated method stub
		try {
			Event e=eventRepo.findById(id).get();
			return e.isDeleted()?null:e;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

	@Override
	public Event updateEvent(Integer id,Event event,String token) {
		// TODO Auto-generated method stub
		
		try{
			String username=jwtService.extractUsername(token);
			User u=userRepo.findByUsername(username).get();
			
			Event e=eventRepo.findById(id).get();
			e.setTitle(event.getTitle());
			e.setDateEvent(event.getDateEvent());
			e.setPlace(event.getPlace());
			e.setDescription(event.getDescription());
			e.setPic(event.getPic());
			
			e.setUpdatedAt(LocalDateTime.now());
			e.setUpdatedBy(u.getAdmin());
			return eventRepo.save(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	private void enableDeletedFilter(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedEventsFilter");
        filter.setParameter("isDeleted", isDeleted);
    }

    private void disableDeletedFilter() {
        Session session = entityManager.unwrap(Session.class);
        session.disableFilter("deletedEventsFilter");
    }

	@Override
	@Scheduled(cron = "1 0 * * * *")
	public void updateEventsStatus() {
		// TODO Auto-generated method stub
		List<Event> events = eventRepo.findAll();
		for(Event e:events) {
			if(e.getDateEvent()!=null) {
				e=updateStatus(e);
			}
		}
	}
	
   
    
}
