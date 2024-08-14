package club_website.auth.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import club_website.auth.Models.Event;
import club_website.auth.Models.MemberEvent;

@Service
public interface MemberEventService {
	public List<MemberEvent> addPresence(Event e);
	public MemberEvent updatePresence(Integer id,boolean presence,String token);
	public List<MemberEvent> getPresence(Integer id);
}
