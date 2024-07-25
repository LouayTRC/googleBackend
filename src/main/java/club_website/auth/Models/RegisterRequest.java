package club_website.auth.Models;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterRequest {

	private String fullname;
	private String username;
	private String mail;
	private Set<Role> roles;
	private Set<Department> departments;
	private String password;
	private String pdp;

}