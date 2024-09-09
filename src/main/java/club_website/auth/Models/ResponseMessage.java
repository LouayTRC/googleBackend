package club_website.auth.Models;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseMessage {
	private String msg;
	private boolean status;

	//private int _status; //1 => success , 2 => infor, 3 => warning , =1 => error
}
