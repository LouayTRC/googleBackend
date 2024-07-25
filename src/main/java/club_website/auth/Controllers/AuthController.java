package club_website.auth.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club_website.auth.Models.AuthenticationRequest;
import club_website.auth.Models.AuthenticationResponse;
import club_website.auth.Models.RegisterRequest;
import club_website.auth.Models.VerificationToken;
import club_website.auth.Services.AuthService;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
		return ResponseEntity.ok(authService.register(request));
	}
	
	@PostMapping("/admin")
	public ResponseEntity<AuthenticationResponse> addAdmin(@RequestBody RegisterRequest request){
		return ResponseEntity.ok(authService.addAdmin(request));
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
		return ResponseEntity.ok(authService.login(request));
	}
	
	@PostMapping("/verify/{token}")
	public ResponseEntity<VerificationToken> verifyToken(@PathVariable String token){
		return ResponseEntity.ok(authService.verifyToken(token));
	}
}
