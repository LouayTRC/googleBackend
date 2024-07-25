package club_website.auth.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club_website.auth.Models.Application;
import club_website.auth.Services.ApplicationService;
import jakarta.websocket.server.PathParam;

@CrossOrigin("*")
@RequestMapping("/api/app")
@RestController
public class ApplicationController {
	
	@Autowired
	private ApplicationService appService;
	
	@GetMapping
	public List<Application> getApps(){
		return appService.getApplications();
	}
	
	@PostMapping()
	public Application addApp(Application app) {
		return appService.addApp(app);
	}
	
	@PutMapping("/{id}/{status}")
	public Application updateStatus(@PathParam("id") Integer id,@PathParam("status") Integer status) {
		return appService.updateStatus(id, status);
	}
}
