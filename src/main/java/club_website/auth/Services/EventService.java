package club_website.auth.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import club_website.auth.Models.Event;

@Service
public interface EventService {
	public Event addEvent(Event e,String token);
	public List<Event> getEvents();
	public Event updateStatus(Event e);
	public void deleteEvent(Integer id,String token);
	public Event getEventById(Integer id);
	public Event updateEvent(Integer id,Event e,String token);
}
