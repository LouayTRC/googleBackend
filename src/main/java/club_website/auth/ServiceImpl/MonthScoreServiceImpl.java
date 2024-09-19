package club_website.auth.ServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import club_website.auth.Config.JwtService;
import club_website.auth.Models.Member;
import club_website.auth.Models.MonthScore;
import club_website.auth.Models.UpdateMsRequest;
import club_website.auth.Models.User;
import club_website.auth.Repositories.MemberRepo;
import club_website.auth.Repositories.MonthScoreRepo;
import club_website.auth.Repositories.UserRepo;
import club_website.auth.Services.MonthScoreService;
import club_website.auth.Services.WorkService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class MonthScoreServiceImpl implements MonthScoreService{
	
	@Autowired
	private MonthScoreRepo monthScoreRepo;
	
	@Autowired
	private MemberRepo memberRepo;
	
	@Autowired
	private WorkService workService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EntityManager entityManager;
	
	@Scheduled(fixedRate = 60000)
	public void keepAliveTask() {
	    // Perform a lightweight operation, like a simple log
	    System.out.println("Keeping app alive");
	}
	
	@Transactional
	@Override
	@Scheduled(cron = "1 0 0 1 * *")
	public void addMonthScore() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Scheduled task started");
			List<Member> members=memberRepo.findAll();
			for(Member m:members) {
				
                LocalDate date=LocalDate.now().minusMonths(1);
                double departScore=workService.calculateAverageScoreForPreviousMonth(m);
                
				MonthScore ms = MonthScore.builder()
		                .member(entityManager.merge(m))
		                .departsPoints(departScore)
		                .year(date.getYear())
		                .month(date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH))
		                .build();
				ms.setScore(ms.calculScore());
				monthScoreRepo.save(ms);
				
				m.setScore(m.calculScore());
				memberRepo.save(m);
				System.out.println("Month Score added for Member : "+m.getUser().getUsername());
			}
			System.out.println("Scheduled task finished");
				
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
		
		
	}

	@Override
	public MonthScore updateMonthScore(Integer id, UpdateMsRequest ms,String token) {
		// TODO Auto-generated method stub
		try {
			String username=jwtService.extractUsername(token);
			User user=userRepo.findByUsername(username).get();
			MonthScore msf=monthScoreRepo.findById(id).get();
			msf.setContribution(ms.getContribution());
			msf.setDiscipline(ms.getDiscipline());
			msf.setScore(msf.calculScore());
			msf.setUpdatedAt(LocalDateTime.now());
			msf.setUpdatedBy(user.getAdmin());
			msf=monthScoreRepo.save(msf);
			
			Member m=memberRepo.findById(msf.getMember().getMember_id()).get();
			m.setScore(m.calculScore());
			m=memberRepo.save(m);			
			return msf;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
		
		
	}

	@Override
	public List<MonthScore> getMsByMember(String token) {
		// TODO Auto-generated method stub
		
		String username=jwtService.extractUsername(token);
		User user=userRepo.findByUsername(username).get();		
		List<MonthScore> ms=monthScoreRepo.findByMember(user.getMember());
		return ms;
	}

	@Override
	public List<MonthScore> getAllMs() {
		// TODO Auto-generated method stub
		return monthScoreRepo.findAll();
	}

	@Override
	public MonthScore getMsById(Integer id) {
		// TODO Auto-generated method stub
		Optional<MonthScore> ms=monthScoreRepo.findById(id);
		return ms.isPresent()?ms.get():null;
	}
	
	
	
	
}
