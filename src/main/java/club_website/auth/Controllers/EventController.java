package club_website.auth.Controllers;
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

import club_website.auth.Models.Event;
import club_website.auth.Models.MemberEvent;
import club_website.auth.Services.EventService;
import club_website.auth.Services.MemberEventService;



@RestController
@RequestMapping("/api/event")
@CrossOrigin("*")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private MemberEventService memberEventService;
	
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Event getEventById(@PathVariable Integer id){
		return eventService.getEventById(id);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MEMBER')")
	public List<Event> getEvents(){
		return eventService.getEvents();
	}
	
	@PostMapping()
	@PreAuthorize("hasAuthority('ADMIN')")
	public Event addEvent(@RequestBody  Event e,@RequestHeader("Authorization") String token){
		return eventService.addEvent(e,token.substring(7));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteEvent(@PathVariable Integer id,@RequestHeader("Authorization") String token) {
		eventService.deleteEvent(id,token.substring(7));
	}
	
	@PutMapping("{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Event updateEvent(@PathVariable Integer id,@RequestBody Event e,@RequestHeader("Authorization") String token) {
		return eventService.updateEvent(id,e,token.substring(7));
	}
	
	@PutMapping("/present/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public MemberEvent memberPresent(@PathVariable Integer id,@RequestHeader("Authorization") String token) {
		return memberEventService.memberPresent(id,token.substring(7));
	}
	
	@PutMapping("/absent/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public MemberEvent memberAbsent(@PathVariable Integer id,@RequestHeader("Authorization") String token) {
		return memberEventService.memberAbsent(id,token.substring(7));
	}
	
	@GetMapping("/presence/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<MemberEvent> getPresence(@PathVariable Integer id) {
		return memberEventService.getPresence(id);
	}
}
