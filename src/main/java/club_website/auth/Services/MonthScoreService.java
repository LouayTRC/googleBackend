package club_website.auth.Services;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import club_website.auth.Models.MonthScore;
import club_website.auth.Models.UpdateMsRequest;

@Service
public interface MonthScoreService {
	public void addMonthScore();
	
	public MonthScore updateMonthScore(Integer id,UpdateMsRequest ms,String token);
	public List<MonthScore> getMsByMember(String token);
	public List<MonthScore> getAllMs();
	public MonthScore getMsById(Integer id);
}
