package club_website.auth.Models;

import lombok.Data;

@Data
public class ChangePwdRequest {
	    private String oldPwd;
	    private String newPwd;

}
