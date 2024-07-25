package club_website.auth.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import club_website.auth.Models.Department;
import club_website.auth.Models.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer>{

	public List<Task> findByDepartment(Department d);

}
