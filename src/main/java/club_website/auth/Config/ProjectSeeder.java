package club_website.auth.Config;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import club_website.auth.Models.Admin;
import club_website.auth.Models.Department;
import club_website.auth.Models.Member;
import club_website.auth.Models.Role;
import club_website.auth.Models.User;
import club_website.auth.Repositories.AdminRepo;
import club_website.auth.Repositories.DepartmentRepo;
import club_website.auth.Repositories.MemberRepo;
import club_website.auth.Repositories.RoleRepo;
import club_website.auth.Repositories.UserRepo;
import club_website.auth.Services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
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
	
	@org.springframework.beans.factory.annotation.Value("${app.owner.username}")
    private String ownerUsername;

    @org.springframework.beans.factory.annotation.Value("${app.owner.password}")
    private String ownerPassword;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		try {
			Set<Role> roles = new HashSet<>();
			
			if (!roleRepo.existsByName("OWNER")) {
				Role r1 = Role.builder().name("OWNER").build();
				roles.add(roleRepo.save(r1));
			} else {
				roles.add(roleRepo.findByName("OWNER"));
			}
			
			if (!roleRepo.existsByName("SUPER ADMIN")) {
				Role r3 = Role.builder().name("SUPER ADMIN").build();
				roles.add(roleRepo.save(r3));
			} else {
				roles.add(roleRepo.findByName("SUPER ADMIN"));
			}
			
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
			
			Optional<User> userBD=userRepo.findByUsername("louuu");
			
			if(!userBD.isPresent()) {
				var user=User.builder()
						.fullname("louay tarchoun")
						.username(ownerUsername)
						.mail("louaytrc2@gmail.com")
						.password(passwordEncoder.encode(ownerPassword))
						.pdp("https://firebasestorage.googleapis.com/v0/b/clubwebsite-9d189.appspot.com/o/users%2Faa.jpg?alt=media&token=d3114a4e-d196-4722-a73f-a382b617cab7")
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

		        
		        Admin admin=Admin.builder().user(userRes).createdAt(LocalDate.now()).build();
		        adminRepo.save(admin);
		
				System.out.println("Owner created successfully !");

			}
			
			System.out.println("Seeding Completed !");
			
		} catch (Exception e) {
			System.err.println("error : " + e.getMessage());
		}
	}
	
}
