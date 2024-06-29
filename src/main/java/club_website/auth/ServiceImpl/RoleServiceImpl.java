package club_website.auth.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club_website.auth.Models.Role;
import club_website.auth.Repositories.RoleRepo;
import club_website.auth.Services.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public Role addRole(Role role) {
		// TODO Auto-generated method stub
		return roleRepo.save(role);
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return roleRepo.findAll();
	}
	
	
}
