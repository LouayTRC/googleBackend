package club_website.auth.ServiceImpl;


import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import club_website.auth.Models.User;
import club_website.auth.Config.JwtService;
import club_website.auth.Models.AuthenticationRequest;
import club_website.auth.Models.AuthenticationResponse;
import club_website.auth.Models.Department;
import club_website.auth.Models.Member;
import club_website.auth.Models.RegisterRequest;
import club_website.auth.Models.Role;
import club_website.auth.Repositories.DepartmentRepo;
import club_website.auth.Repositories.MemberRepo;
import club_website.auth.Repositories.RoleRepo;
import club_website.auth.Repositories.UserRepo;
import club_website.auth.Services.AuthService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	
	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	private final MemberRepo memberRepo;
	private final DepartmentRepo departmentRepo;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	
	@Override
	public AuthenticationResponse register(RegisterRequest request) {
		// TODO Auto-generated method stub
		System.out.println("req"+request);
		var user=User.builder()
				.fullname(request.getFullname())
				.username(request.getUsername())
				.mail(request.getMail())
				.password(passwordEncoder.encode(request.getPassword()))
				.pdp(request.getPdp())
				.build();
		
		Set<Role> userRoles = new HashSet<>();
        for (Role role : request.getRoles()) {
            Role existingRole =roleRepo.findById(role.getId()).orElseThrow();
            System.out.println(existingRole);
            userRoles.add(existingRole);
        }
        user.setRoles(userRoles);
        
        Set<Department> userDeps = new HashSet<>();
        for (Department dep : request.getDepartments()) {
        	Department existingDep =departmentRepo.findById(dep.getId()).orElseThrow();
        	 System.out.println(existingDep);
        	userDeps.add(existingDep);
        }
        user.setDepartments(userDeps);
        System.out.println(user);
		userRepo.save(user);
		var jwtToken=jwtService.generateToken(user);
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
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

}
