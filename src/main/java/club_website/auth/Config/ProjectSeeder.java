package club_website.auth.Config;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import club_website.auth.Models.Admin;
import club_website.auth.Models.Department;
import club_website.auth.Models.Member;
import club_website.auth.Models.RegisterRequest;
import club_website.auth.Models.Role;
import club_website.auth.Models.User;
import club_website.auth.Repositories.AdminRepo;
import club_website.auth.Repositories.DepartmentRepo;
import club_website.auth.Repositories.MemberRepo;
import club_website.auth.Repositories.RoleRepo;
import club_website.auth.Repositories.UserRepo;
import club_website.auth.Services.AuthService;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ProjectSeeder implements ApplicationRunner {
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private MemberRepo memberRepo;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		try {
			Set<Role> roles = new HashSet<>();
			if (!roleRepo.existsByName("ADMIN")) {
				Role r1 = Role.builder().name("ADMIN").build();
				roles.add(roleRepo.save(r1));
			} else {
				roles.add(roleRepo.findByName("ADMIN"));
			}
			
			if (!roleRepo.existsByName("MEMBER")) {
				Role r2 = Role.builder().name("MEMBER").build();
				roles.add(roleRepo.save(r2));
			} else {
				roles.add(roleRepo.findByName("MEMBER"));
			}
			
			if (!roleRepo.existsByName("SUPER ADMIN")) {
				Role r3 = Role.builder().name("SUPER ADMIN").build();
				roles.add(roleRepo.save(r3));
			} else {
				roles.add(roleRepo.findByName("SUPER ADMIN"));
			}

			Set<Department> deps = new HashSet<>();
			if (!departmentRepo.existsByName("Dev")) {
				Department d1 = Department.builder().name("Dev").build();
				deps.add(departmentRepo.save(d1));
			} else {
				deps.add(departmentRepo.findByName("Dev"));
			}
			
			if (!departmentRepo.existsByName("Media")) {
				Department d2 = Department.builder().name("Media").build();
				deps.add(departmentRepo.save(d2));
			} else {
				deps.add(departmentRepo.findByName("Media"));
			}
			
			if (!departmentRepo.existsByName("Rh")) {
				Department d3 = Department.builder().name("Rh").build();
				deps.add(departmentRepo.save(d3));
			} else {
				deps.add(departmentRepo.findByName("Rh"));
			}
			
			var user=User.builder()
					.fullname("louay tarchoun")
					.username("louuu")
					.mail("louaytrc@gmail.com")
					.password(passwordEncoder.encode("louuu"))
					.pdp("test")
					.enabled(true)
					.build();
			
	        user.setRoles(roles);
	        User userRes=userRepo.save(user);
	        System.out.println("user"+userRes);
	       	
        	Set<Department> memberDeps = new HashSet<>();
            for (Department dep : departmentRepo.findAll()) {
            	 memberDeps.add(dep);
            }
            
            User res=userRepo.findByUsername(user.getUsername()).get();
            System.out.println("usr"+res);
            
            
            Member m=Member.builder().user(res).departments(memberDeps).build();
            System.out.println("mem"+m);
    		memberRepo.save(m);

	        
	        Admin admin=Admin.builder().user(userRes).createdAt(new Date()).build();
	        adminRepo.save(admin);
	
			System.out.println("Admin user created successfully !");
		} catch (Exception e) {
			System.err.println("Failed to create admin user: " + e.getMessage());
		}
		
		System.out.println("Seeding completed!");
	}
}
