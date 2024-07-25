package club_website.auth.ServiceImpl;


import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import club_website.auth.Config.JwtService;
import club_website.auth.Models.Department;
import club_website.auth.Models.Member;
import club_website.auth.Models.Task;
import club_website.auth.Models.User;
import club_website.auth.Models.Work;
import club_website.auth.Repositories.DepartmentRepo;
import club_website.auth.Repositories.MemberRepo;
import club_website.auth.Repositories.TaskRepo;
import club_website.auth.Repositories.UserRepo;
import club_website.auth.Repositories.WorkRepo;
import club_website.auth.Services.TaskService;
import club_website.auth.Services.UserService;
import club_website.auth.Services.WorkService;
@Service
public class WorkServiceImpl implements WorkService{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskRepo taskRepo;
	
	@Autowired
	private WorkRepo workRepo;
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private MemberRepo memberRepo;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public List<Work> getWorksByTask(Task task) {
        return workRepo.findByTask(task);
    }
	
	@Override
	public List<Work> addDepos(Task task) throws Exception{
		// TODO Auto-generated method stub
        try {
        		System.out.println("here");
        		List<Member> members=userService.getMembersByDepart(task.getDepartment());
        		System.out.println("members"+members);
        		List<Work> works=new ArrayList<Work>();
    			for(Member m:members) {
        				Work work=Work.builder().member(m).task(task).dateDepo(LocalDate.now())
                				.build();
                		works.add(workRepo.save(work));
    			}
        		System.out.println("depos"+works);
        		return works;
        }
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
		
        
	}

	@Override
	public List<Work> getAllWorks() {
		// TODO Auto-generated method stub
		return workRepo.findAllWorks();
	}

	@Override
	public Work getWorkById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Work> work=workRepo.findById(id);
		return work.isPresent()?work.get():null;
	}

	@Override
	public Work submitWork(Integer id,String url,String token) {
		// TODO Auto-generated method stub
		
		try {
			String username=jwtService.extractUsername(token);
			User u=userRepo.findByUsername(username).get();
			Work work=getWorkById(id);
			if(work!=null && work.isStatus() && work.getTask().isStatus() && work.getMember().getUser().getId()==u.getId() && !work.getTask().isDeleted() ) {
				work.setUrl(url);
				work.setStatus(false);
				work.setDateDepo(LocalDate.now());
				work=workRepo.save(work);
			}
			return work;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("eerror submitting work");
			return null;
		}
	}

	@Override
	public Work updateNote(Integer id,double note,String token) {
		// TODO Auto-generated method stub
		try {
			System.out.println("here'");
			String username=jwtService.extractUsername(token);
			System.out.println("isername"+username);
			User u=userRepo.findByUsername(username).get();
			System.out.println("iser"+u);
			Work work=getWorkById(id);
			System.out.println("work"+work);
			
			if(work!=null  && !work.getUrl().equals("") && work.getTask().isStatus() ) {
				work.setNote(note);
				work.setUpdatedAt(LocalDate.now());
				work.setUpdatedBy(u.getAdmin());
				work=workRepo.save(work);
			}
			return work;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("eerror submitting note");
			return null;
		}
	}

	@Override
	public List<Work> getWorksByMember(Integer id) {
		// TODO Auto-generated method stub
		Optional<Member> m=memberRepo.findById(id);
		if(m.isPresent()) {
			return workRepo.findByMember(m.get());
		}
		else return null;
	}
	
	
	
	public double calculateAverageScoreForPreviousMonth(Member member) {
		LocalDate date=LocalDate.now().minusMonths(1);;
        YearMonth previousMonth = YearMonth.of(date.getYear(), date.getMonth());
        LocalDate startDate = previousMonth.atDay(1);
        LocalDate endDate = previousMonth.atEndOfMonth();
        
        List<Work> works = workRepo.findWorksByMemberAndPreviousMonth(member,startDate,endDate);
        if (works.isEmpty()) {
            return 0.0;
        }
        double totalScore = works.stream().mapToDouble(Work::getNote).sum();
        return totalScore / works.size();
    }
}
	

