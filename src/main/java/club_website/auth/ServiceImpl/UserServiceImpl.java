package club_website.auth.ServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import club_website.auth.Models.Admin;
import club_website.auth.Models.Department;
import club_website.auth.Models.Event;
import club_website.auth.Models.Member;
import club_website.auth.Models.ResponseMessage;
import club_website.auth.Models.User;
import club_website.auth.Repositories.AdminRepo;
import club_website.auth.Repositories.MemberRepo;
import club_website.auth.Repositories.UserRepo;
import club_website.auth.Services.MailService;
import club_website.auth.Services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private MemberRepo memberRepo;
	
	@Autowired 
	private AdminRepo adminRepo;
	
	@Autowired
	private MailService mailService;

	@Override
	public List<Member> getAllMembers() {
		// TODO Auto-generated method stub
		return memberRepo.findAll();
	}
	
	@Override
	public List<Admin> getAllAdmins() {
		// TODO Auto-generated method stub
		return adminRepo.findAll();
	}
	
	
	@Override
	public List<Member> getMembersByDepart(Department department) {
		// TODO Auto-generated method stub
		List<Member> members=memberRepo.findByDepartments(department);
		System.out.println(members);
		return members;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		try {
			userRepo.deleteById(id);
			System.out.println("deleted successfully");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("error deleting user");
		}
		
	}

	@Override
	public Set<Member> getMembersByEvent(Event e) {
		// TODO Auto-generated method stub
		Set<Member> members=new HashSet<>();
		for(Department dep:e.getDepartments()) {
			members.addAll(memberRepo.findByDepartments(dep));
		}
		return members;
	}

	@Override
	public List<Member> getLeaderBoard() {
		// TODO Auto-generated method stub
		List<Member> members=memberRepo.findAll();
		Collections.sort(members);
		return members;
	}

	@Override
	public ResponseEntity activateUser(Integer id,boolean status) {
		// TODO Auto-generated method stub
		try {
			User u=userRepo.findById(id).get();
			if(!u.hasAuthority("OWNER")) {
				u.setEnabled(status);
				userRepo.save(u);
				mailService.accountStatusUpdated(u);
				ResponseMessage msg=new ResponseMessage("account status updated successfully",true);
				return new ResponseEntity(msg, HttpStatus.OK);
			}
			ResponseMessage msg=new ResponseMessage("OWNER account can't be modified ",false);
			return new ResponseEntity(msg, HttpStatus.FORBIDDEN);
			
		}catch (Exception e) {
			// TODO: handle exception
			ResponseMessage msg=new ResponseMessage("cannot update Status",false);
			return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
		}
		
		
	}

	@Override
	public Admin getAdminByUsername(String username) {
		// TODO Auto-generated method stub
		Optional<User> user=userRepo.findByUsername(username);
		if(user.isPresent()) {
			return adminRepo.findByUser(user.get());
		}
		return null;
	}
	
	
	
	
}
