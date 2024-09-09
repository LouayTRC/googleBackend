package club_website.auth.Models;


import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
  
  private String token;
  private User user;
  private boolean error;
  private String message;

}
