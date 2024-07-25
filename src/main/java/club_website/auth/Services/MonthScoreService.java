package club_website.auth.Services;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import club_website.auth.Models.MonthScore;

@Service
public interface MonthScoreService {
	public void addMonthScore();
	
	public MonthScore updateMonthScore(Integer id,MonthScore ms);
	public List<MonthScore> getMsByMember(Integer id);
}
