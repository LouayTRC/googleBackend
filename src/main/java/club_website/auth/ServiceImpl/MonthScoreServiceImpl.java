package club_website.auth.ServiceImpl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import club_website.auth.Models.Member;
import club_website.auth.Models.MonthScore;
import club_website.auth.Repositories.MemberRepo;
import club_website.auth.Repositories.MonthScoreRepo;
import club_website.auth.Services.MonthScoreService;
import club_website.auth.Services.WorkService;

@Service
public class MonthScoreServiceImpl implements MonthScoreService{
	
	@Autowired
	private MonthScoreRepo monthScoreRepo;
	
	@Autowired
	private MemberRepo memberRepo;
	
	@Autowired
	private WorkService workService;

	@Override
	@Scheduled(cron = "1 0 0 1 * *", zone = "Africa/Tunis")
	public void addMonthScore() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Scheduled task started");
			List<Member> members=memberRepo.findAll();
			for(Member m:members) {
				
                LocalDate date=LocalDate.now().minusMonths(1);
                double departScore=workService.calculateAverageScoreForPreviousMonth(m);
                
				MonthScore ms = MonthScore.builder()
		                .member(m)
		                .departsPoints(departScore)
		                .year(date.getYear())
		                .month(date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH))
		                .build();
				ms.setScore(ms.calculScore());
				monthScoreRepo.save(ms);
				System.out.println("Month Score added for Member : "+m.getUser().getUsername());
			}
			System.out.println("Scheduled task finished");
				
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
		
		
	}

	@Override
	public MonthScore updateMonthScore(Integer id, MonthScore ms) {
		// TODO Auto-generated method stub
		try {
			MonthScore msf=monthScoreRepo.findById(id).get();
			msf.setContribution(ms.getContribution());
			msf.setDiscipline(ms.getDiscipline());
			msf.setScore(msf.calculScore());
			msf=monthScoreRepo.save(msf);
			
			
			Member m=memberRepo.findById(msf.getMember().getMember_id()).get();
			m.setScore(m.calculScore());
			memberRepo.save(m);
			
			return msf;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
		
		
	}

	@Override
	public List<MonthScore> getMsByMember(Integer id) {
		// TODO Auto-generated method stub
		Member m=memberRepo.findById(id).get();
		
		List<MonthScore> ms=monthScoreRepo.findByMember(m);
		return ms;
	}

	
	
}
