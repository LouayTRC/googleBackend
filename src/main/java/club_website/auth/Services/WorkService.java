package club_website.auth.Services;



import java.util.List;

import org.springframework.stereotype.Service;

import club_website.auth.Models.Department;
import club_website.auth.Models.Member;
import club_website.auth.Models.Task;
import club_website.auth.Models.User;
import club_website.auth.Models.Work;

@Service
public interface WorkService {
	public List<Work> addDepos(Task t) throws Exception;
	public List<Work> getWorksByTask(Task task);
	public List<Work> getAllWorks();
	public List<Work> getWorksByMember(Integer id);
	public Work getWorkById(Integer id);
	public Work submitWork(Integer id,String work,String token);
	public Work updateNote(Integer id,double note,String token);
	public double calculateAverageScoreForPreviousMonth(Member member);
}
