package club_website.auth.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import club_website.auth.Models.Department;
import club_website.auth.Models.Member;
import club_website.auth.Models.User;
@Service
public interface UserService {
	public List<Member> getAllMembers();
	public List<User> getAllUsers();
	public List<User> getAllAdmins();
	public List<User> getUsersByDepart(Department dep);
	public void deleteUser(Integer id);
	
}
