package club_website.auth.ServiceImpl;

import java.time.LocalDate;
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

@Service
public class ApplicationServiceImpl implements ApplicationService{
		
	@Autowired
	private AppRepo appRepo;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public Application addApp(Application app) {
		// TODO Auto-generated method stub
		return appRepo.save(app);
	}

	@Override
	public List<Application> getApplications() {
		// TODO Auto-generated method stub
		return appRepo.findAll();
	}

	@Override
	public Application updateStatus(Integer id,Integer status,String token) {
		// TODO Auto-generated method stub
		try {
			
			String username=jwtService.extractUsername(token);
			User user=userRepo.findByUsername(username).get();
			
			Optional<Application> app=appRepo.findById(id);
			if(app.isPresent()) {
				Application aa=app.get();
				aa.setStatus(status);
				aa.setUpdatedAt(LocalDate.now());
				aa.setUpdatedBy(user.getAdmin());
				return appRepo.save(aa);
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		
	}
	
}
