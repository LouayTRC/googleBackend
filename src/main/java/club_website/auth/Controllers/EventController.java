package club_website.auth.Controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club_website.auth.Models.Event;
import club_website.auth.Services.EventService;



@RestController
@RequestMapping("/api/event")
@CrossOrigin("*")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	
	@GetMapping("/{id}")
	public Event getEventById(@PathVariable Integer id){
		return eventService.getEventById(id);
	}
	
	@GetMapping
	public List<Event> getEvents(){
		return eventService.getEvents();
	}
	
	@PostMapping()
	public Event addEvent(@RequestBody  Event e){
		return eventService.addEvent(e);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEvent(@PathVariable Integer id) {
		eventService.deleteEvent(id);
	}
}
