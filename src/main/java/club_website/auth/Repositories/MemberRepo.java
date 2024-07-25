package club_website.auth.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import club_website.auth.Models.Department;
import club_website.auth.Models.Member;
import club_website.auth.Models.User;
@Repository
public interface MemberRepo extends JpaRepository<Member,Integer>{
	public List<Member> findByDepartments(Department dep);
	public Member findByUser(User user);
}
