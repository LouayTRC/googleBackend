package club_website.auth.Config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
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
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ProjectSeeder implements ApplicationRunner {
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
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

		
	}
	
}
