package club_website.auth.Services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import club_website.auth.Models.Admin;
import club_website.auth.Models.Department;
import club_website.auth.Models.Event;
import club_website.auth.Models.Member;
import club_website.auth.Models.User;
@Service
public interface UserService {
	public List<Member> getAllMembers();
	public List<Member> getLeaderBoard();
	public List<User> getAllUsers();
	public List<Admin> getAllAdmins();
	public List<Member> getMembersByDepart(Department dep);
	public Set<Member> getMembersByEvent(Event e);
	public void deleteUser(Integer id);
	public void activateMember(Integer user_id);
}
