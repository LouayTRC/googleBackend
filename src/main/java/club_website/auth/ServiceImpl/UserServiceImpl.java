package club_website.auth.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club_website.auth.Models.Department;
import club_website.auth.Models.Member;
import club_website.auth.Models.User;
import club_website.auth.Repositories.UserRepo;
import club_website.auth.Services.UserService;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public List<Member> getAllMembers() {
		// TODO Auto-generated method stub
		System.out.println("here");
		List<User> users=userRepo.findAll();
		System.out.println(users);
		List<Member> members=new ArrayList<>();
		for(User u:users) {
			
			if(u.hasAuthority("MEMBER")) {
				members.add((Member)u);
			}
		}
		return members;
	}
	
	@Override
	public List<User> getAllAdmins() {
		// TODO Auto-generated method stub
		System.out.println("here");
		List<User> users=userRepo.findAll();
		System.out.println(users);
		List<User> admins=new ArrayList<>();
		for(User u:users) {
			
			if(u.hasAuthority("ADMIN")) {
				admins.add(u);
			}
		}
		return admins;
	}
	
	
	@Override
	public List<User> getUsersByDepart(Department department) {
		// TODO Auto-generated method stub
		List<User> users=userRepo.findByDepartments(department);
		System.out.println(users);
		return users;
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
	
	
	
}
