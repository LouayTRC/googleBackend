package club_website.auth.ServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club_website.auth.Config.JwtService;
import club_website.auth.Models.Application;
import club_website.auth.Models.User;
import club_website.auth.Repositories.AppRepo;
import club_website.auth.Repositories.UserRepo;
import club_website.auth.Services.ApplicationService;
import club_website.auth.Services.MailService;

@Service
public class ApplicationServiceImpl implements ApplicationService{
		
	@Autowired
	private AppRepo appRepo;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private MailService mailService;
	
	
	@Override
	public Application addApp(Application app) {
		// TODO Auto-generated method stub
		app.setCreatedAt(LocalDateTime.now());
		System.out.println("app"+app);
		return appRepo.save(app);
	}

	@Override
	public List<Application> getApplications() {
		// TODO Auto-generated method stub
		return appRepo.findAll();
	}

	@Override
	public boolean updateStatus(Integer id,Integer status,String token) {
		// TODO Auto-generated method stub
		try {
			System.out.println("aa"+id);
			String username=jwtService.extractUsername(token);
			User user=userRepo.findByUsername(username).get();
			
			Optional<Application> app=appRepo.findById(id);
			System.out.println("app"+app.get());
			if(app.isPresent()) {
				Application aa=app.get();
				if(aa.getStatus()==0) {
					aa.setStatus(status);
					aa.setUpdatedAt(LocalDateTime.now());
					aa.setUpdatedBy(user.getAdmin());
					appRepo.save(aa);
					if(status==1) {
						System.out.println("lena");
						mailService.sendApplicationAccept(aa);
					}
					if(status==-1) {
						mailService.sendApplicationRefuse(aa);
					}
					return true;
				}
				return false;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		
	}

	@Override
	public Application getApplicationById(Integer id) {
		// TODO Auto-generated method stub.
		Application app=appRepo.findById(id).get();
		return app;
	}
	
}
