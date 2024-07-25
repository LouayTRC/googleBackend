package club_website.auth.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import club_website.auth.Models.Application;
@Service
public interface ApplicationService {
	public Application addApp(Application app);
	public List<Application> getApplications();
	public Application updateStatus(Integer id,Integer status);
}
