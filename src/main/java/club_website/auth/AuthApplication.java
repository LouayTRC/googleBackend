package club_website.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
		System.out.println("works");
		System.out.println("DB URL: " + System.getenv("DB_URL"));
	    System.out.println("DB Username: " + System.getenv("DB_USERNAME"));
	}

}
