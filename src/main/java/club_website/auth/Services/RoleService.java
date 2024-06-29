package club_website.auth.Services;

import java.util.List;


import org.springframework.stereotype.Service;

import club_website.auth.Models.Role;

@Service
public interface RoleService {
	
	public Role addRole(Role role);
	public List<Role> getRoles();
}		
