package club_website.auth.Services;


import org.springframework.stereotype.Service;
import club_website.auth.Models.AuthenticationRequest;
import club_website.auth.Models.AuthenticationResponse;
import club_website.auth.Models.ChangePwdRequest;
import club_website.auth.Models.RegisterRequest;
import club_website.auth.Models.ResponseMessage;
import club_website.auth.Models.VerificationToken;

@Service
public interface AuthService {
	public AuthenticationResponse register(RegisterRequest request);
	public AuthenticationResponse addAdmin(RegisterRequest request,String token);
	public AuthenticationResponse login(AuthenticationRequest request);
	public VerificationToken verifyToken(String token);
	public boolean changePassword(ChangePwdRequest request,String token);
}
