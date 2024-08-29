package club_website.auth.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Application> getApps(){
		return appService.getApplications();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Application getAppById(@PathVariable Integer id){
		return appService.getApplicationById(id);
	}
	
	@PostMapping("/add")
	public Application addApp(@RequestBody Application app) {
		return appService.addApp(app);
	}
	
	@PutMapping("/update/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public boolean updateStatus(@PathVariable Integer id,@RequestBody Integer status,@RequestHeader("Authorization") String token) {
		return appService.updateStatus(id, status,token.substring(7));
	}
}
