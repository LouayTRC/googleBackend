package club_website.auth.Services;

import org.springframework.stereotype.Service;

import club_website.auth.Models.Application;
import club_website.auth.Models.Mail;
import club_website.auth.Models.User;

@Service
public interface MailService {
	public boolean contactMe(Mail m);
	public boolean sendApplicationAccept(Application app);
	public boolean sendApplicationRefuse(Application app);
	public boolean creationAccount(User user);
	public boolean accountStatusUpdated(User user);
}
