package club_website.auth.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import club_website.auth.Models.Task;
import club_website.auth.Models.Work;
@Repository
public interface WorkRepo extends JpaRepository<Work, Integer>{
	List<Work> findByTask(Task task);
}
