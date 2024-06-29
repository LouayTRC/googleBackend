package club_website.auth.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import club_website.auth.Models.Department;
import club_website.auth.Models.User;

public interface UserRepo extends JpaRepository<User,Integer>{
	Optional<User> findByUsername(String username);
	public List<User> findByDepartments(Department dep);
}
