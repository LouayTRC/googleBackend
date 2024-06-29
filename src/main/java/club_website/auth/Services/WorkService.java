package club_website.auth.Services;



import java.util.List;

import org.springframework.stereotype.Service;

import club_website.auth.Models.Department;

import club_website.auth.Models.Task;
import club_website.auth.Models.Work;

@Service
public interface WorkService {
	public List<Work> addDepos(Task t) throws Exception;
	public List<Work> getWorksByTask(Task task);
	public List<Work> getAllWorks();
	public Work getWorkById(Integer id);
	public Work submitWork(Work w);
	public Work updateScore(Work w);
}
