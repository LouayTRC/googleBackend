package club_website.auth.ServiceImpl;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import club_website.auth.Models.User;
import club_website.auth.Models.VerificationToken;
import club_website.auth.Config.JwtService;
import club_website.auth.Models.Admin;
import club_website.auth.Models.AuthenticationRequest;
import club_website.auth.Models.AuthenticationResponse;
import club_website.auth.Models.ChangePwdRequest;
import club_website.auth.Models.Department;
import club_website.auth.Models.Member;
import club_website.auth.Models.RegisterRequest;
import club_website.auth.Models.Role;
import club_website.auth.Repositories.AdminRepo;
import club_website.auth.Repositories.DepartmentRepo;
import club_website.auth.Repositories.MemberRepo;
import club_website.auth.Repositories.RoleRepo;
import club_website.auth.Repositories.UserRepo;
import club_website.auth.Services.AuthService;
import club_website.auth.Services.MailService;
import club_website.auth.Services.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	
	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	private final MemberRepo memberRepo;
	private final AdminRepo adminRepo;
	private final UserService userService;
	private final DepartmentRepo departmentRepo;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final MailService mailService;
	
	@Override
	public AuthenticationResponse register(RegisterRequest request) {
		// TODO Auto-generated method stub
		
		var user=User.builder()
				.fullname(request.getFullname())
				.username(request.getUsername())
				.mail(request.getMail())
				.password(passwordEncoder.encode(request.getPassword()))
				.pdp(request.getPdp())
				.build();
		
		Set<Role> userRoles = new HashSet<>();
		Role existingRole =roleRepo.findByName("MEMBER");
		userRoles.add(existingRole);
        user.setRoles(userRoles);
        
        Set<Department> memberDeps = new HashSet<>();
        for (Department dep : request.getDepartments()) {
        	Department existingDep =departmentRepo.findById(dep.getId()).orElseThrow();
        	 memberDeps.add(existingDep);
        }
		User userRes=userRepo.save(user);
		Member m=Member.builder().user(userRes).departments(memberDeps).build();
		memberRepo.save(m);
		var jwtToken=jwtService.generateToken(user);
		mailService.creationAccount(userRes);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

	@Override
	public AuthenticationResponse login(AuthenticationRequest request) {
		// TODO Auto-generated method stub
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
		);
		var user=userRepo.findByUsername(request.getUsername()).orElseThrow();
		var jwtToken=jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).user(user).build();
	}

	@Override
	public VerificationToken verifyToken(String token) {
		// TODO Auto-generated method stub
		try {
			
		
		String username=jwtService.extractUsername(token);
		Optional<User> user=userRepo.findByUsername(username);
		if(user.isPresent() && jwtService.isTokenValid(token, user.get())) {
			return VerificationToken.builder().user(user.get()).loggedIn(true).build();		
		}
		else {
			return VerificationToken.builder().user(null).loggedIn(false).build();
		}
		} catch (Exception e) {
			return VerificationToken.builder().user(null).loggedIn(false).build();
		}
	}
	
	@Override
	public AuthenticationResponse addAdmin(RegisterRequest request,String token) {
		// TODO Auto-generated method stub

		 String username=jwtService.extractUsername(token);
	     Admin creator=userService.getAdminByUsername(username);
	        
	        
		var user=User.builder()
				.fullname(request.getFullname())
				.username(request.getUsername())
				.mail(request.getMail())
				.password(passwordEncoder.encode(request.getPassword()))
				.pdp(request.getPdp())
				.build();
		
		Set<Role> userRoles = new HashSet<>();
		for(Role r:request.getRoles()) {
			Role existingRole =roleRepo.findById(r.getId()).get();
			if(!existingRole.getName().equals("OWNER")) {
				if(!existingRole.getName().equals("SUPER ADMIN")) {
					userRoles.add(existingRole);
				}
				else {
					if(creator.getUser().hasAuthority("OWNER") || creator.getUser().hasAuthority("SUPER ADMIN")) {
						userRoles.add(existingRole);
					}
				}
			}
		}
		
        user.setRoles(userRoles);
        user.setEnabled(true);
        User userRes=userRepo.save(user);
        if(user.hasAuthority("MEMBER")) {
        	
        	Set<Department> memberDeps = new HashSet<>();
            for (Department dep : departmentRepo.findAll()) {
            	 memberDeps.add(dep);
            }
            Member m=Member.builder().user(userRes).departments(memberDeps).build();
            System.out.println(m);
    		memberRepo.save(m);
        }
        
       
        
        Admin admin=Admin.builder().user(userRes).createdAt(LocalDateTime.now()).createdBy(creator).build();
        adminRepo.save(admin);
		
		
		var jwtToken=jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

	@Override
	public boolean changePassword(ChangePwdRequest request,String token) {
		// TODO Auto-generated method stub
		String username=jwtService.extractUsername(token);
		User user=userRepo.findByUsername(username).get();
		if(user!=null) {
			if (passwordEncoder.matches(request.getOldPwd(), user.getPassword())) {
	            user.setPassword(passwordEncoder.encode(request.getNewPwd()));
	            userRepo.save(user);
	            return true;
	        } else {
	        	return false;
	        }
		}
		return false;
	}
	
	
	
}
