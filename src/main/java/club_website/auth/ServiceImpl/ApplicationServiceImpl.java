package club_website.auth.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club_website.auth.Models.Application;
import club_website.auth.Repositories.AppRepo;
import club_website.auth.Services.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService{
		
	@Autowired
	private AppRepo appRepo;
	
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
	public Application updateStatus(Integer id,Integer status) {
		// TODO Auto-generated method stub
		try {
			Optional<Application> app=appRepo.findById(id);
			if(app.isPresent()) {
				Application aa=app.get();
				aa.setStatus(status);
				return appRepo.save(aa);
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		
	}
	
}
