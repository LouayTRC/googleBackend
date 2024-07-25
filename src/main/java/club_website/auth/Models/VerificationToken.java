package club_website.auth.Models;

import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationToken {
	
	private boolean loggedIn;
	private User user;
}
