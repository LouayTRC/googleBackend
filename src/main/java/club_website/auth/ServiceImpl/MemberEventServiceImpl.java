package club_website.auth.ServiceImpl;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club_website.auth.Config.JwtService;
import club_website.auth.Models.Event;
import club_website.auth.Models.Member;
import club_website.auth.Models.MemberEvent;
import club_website.auth.Models.User;
import club_website.auth.Repositories.EventRepo;
import club_website.auth.Repositories.MemberEventRepo;
import club_website.auth.Repositories.UserRepo;
import club_website.auth.Services.MemberEventService;
import club_website.auth.Services.UserService;

@Service
public class MemberEventServiceImpl implements MemberEventService {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MemberEventRepo memberEventRepo;
	
	@Autowired 
	private  EventRepo eventRepo;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public List<MemberEvent> addPresence(Event e) {
		// TODO Auto-generated method stub
		Set<Member> members=userService.getMembersByEvent(e);
		List<MemberEvent> membersEvent=new ArrayList<>();
		for(Member m:members) {
			MemberEvent me=MemberEvent.builder().event(e).member(m).build();
			membersEvent.add(memberEventRepo.save(me));
		}
		
		return membersEvent;
	}


	@Override
	public List<MemberEvent> getPresence(Integer id) {
		// TODO Auto-generated method stub
		Event e=eventRepo.findById(id).get();
		return memberEventRepo.findByEvent(e);
	}

	@Override
	public MemberEvent updatePresence(Integer id,boolean presence,String token) {
		// TODO Auto-generated method stub
		String username=jwtService.extractUsername(token);
		User u=userRepo.findByUsername(username).get();
		Optional<MemberEvent> me=memberEventRepo.findById(id);
		if (me.isPresent()) {
			me.get().setPresent(presence);
			me.get().setUpdatedAt(LocalDateTime.now());
			me.get().setUpdatedBy(u.getAdmin());
			return memberEventRepo.save(me.get());
		}
		return null;
	}
	
	
	
	
	

	
	
}
