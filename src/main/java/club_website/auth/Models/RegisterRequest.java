package club_website.auth.Models;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

	private String fullname;
	private String username;
	private String mail;
	private Set<Role> roles;
	private Set<Department> departments;
	private String password;
	private String pdp;

}