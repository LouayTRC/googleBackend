package club_website.auth.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import club_website.auth.Models.Application;
import club_website.auth.Models.Mail;
import club_website.auth.Models.User;
import club_website.auth.Services.MailService;

@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String email;
	
	@Override
	public boolean contactMe(Mail m) {
		// TODO Auto-generated method stub
		try {
			SimpleMailMessage mail=new SimpleMailMessage();
			mail.setFrom(m.getFrom());
			mail.setSubject(m.getSubject());
			mail.setTo(email);
			mail.setText(m.getText());
			mailSender.send(mail);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}


	@Override
	public boolean sendApplicationAccept(Application app) {
		// TODO Auto-generated method stub
		try {
			SimpleMailMessage mail=new SimpleMailMessage();
			mail.setFrom(email);
			mail.setSubject("Application Response");
			mail.setTo(app.getMail());
			mail.setText("Dear "+app.getFullname()+" , \nWe are pleased to inform you that your application for membership has been accepted! Welcome to google club community.\nWe look forward to your active participation and contribution.\nPlease let us know if you have any questions or need further information.\nBest regards,Google club ");
			mailSender.send(mail);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean sendApplicationRefuse(Application app) {
		// TODO Auto-generated method stub
		try {
			SimpleMailMessage mail=new SimpleMailMessage();
			mail.setFrom(email);
			mail.setSubject("Application Response");
			mail.setTo(app.getMail());
			mail.setText("Dear "+app.getFullname()+",\nThank you for your interest in joining google club .\nAfter careful consideration, we regret to inform you that we are unable to accept your application at this time.\nWe encourage you to stay involved and apply again in the future.\nWe appreciate your understanding and wish you the best in your efforts.\nBest regards, google club");
			mailSender.send(mail);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean creationAccount(User user) {
		// TODO Auto-generated method stub
		try {
			SimpleMailMessage mail=new SimpleMailMessage();
			mail.setFrom(email);
			mail.setSubject("Account Created");
			mail.setTo(user.getMail());
			mail.setText("Dear "+user.getFullname()+",\nThank you for registering with Google club . Your account has been successfully created; however, it is currently disabled..\nTo ensure that only verified members have access, an admin will review your membership status. Once your membership is confirmed, your account will be enabled for connection.\nWe will notify you once the process is complete. If you have any questions in the meantime, feel free to reach out.\nBest regards, Google Club");
			mailSender.send(mail);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean accountStatusUpdated(User user) {
		// TODO Auto-generated method stub
		try {
			SimpleMailMessage mail=new SimpleMailMessage();
			mail.setFrom(email);
			mail.setTo(user.getMail());
			mail.setSubject("Account Status Updated");
			if(user.isEnabled()) {
				mail.setText("Dear "+user.getFullname()+",\nWe are pleased to inform you that your membership has been confirmed, and your account is now activated! You can now log in and start enjoying all the benefits of being a member of Google Club.\nIf you have any questions or need assistance, please don't hesitate to contact us.\nWelcome aboard!\nBest regards,Google Club");
			}else {
				mail.setText("Dear "+user.getFullname()+",\nThank you for your interest in joining Google Club. \nAfter reviewing your membership application, we regret to inform you that we are unable to approve your account at this time.\nAs a result, your account remains disabled.\nWe encourage you to stay engaged and reapply in the future.\nIf you have any questions, please feel free to reach out.\nBest regards,Google Club");
			}
			mailSender.send(mail);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	
	
}
