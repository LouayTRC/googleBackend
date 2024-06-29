package club_website.auth.Services;


import org.springframework.stereotype.Service;
import club_website.auth.Models.AuthenticationRequest;
import club_website.auth.Models.AuthenticationResponse;
import club_website.auth.Models.RegisterRequest;

@Service
public interface AuthService {
	public AuthenticationResponse register(RegisterRequest request);
	public AuthenticationResponse login(AuthenticationRequest request);
}
