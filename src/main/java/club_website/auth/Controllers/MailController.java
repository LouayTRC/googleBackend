package club_website.auth.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club_website.auth.Models.Mail;
import club_website.auth.Services.MailService;

@RequestMapping("/api/mail")
@CrossOrigin("*")
@RestController
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	@PostMapping
	public boolean contactMe(@RequestBody Mail m) {
		return mailService.contactMe(m);
	}

}
