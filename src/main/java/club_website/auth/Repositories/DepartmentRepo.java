package club_website.auth.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import club_website.auth.Models.Department;
@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer>{
	public boolean existsByName(String name);
	public Department findByName(String name);
}
