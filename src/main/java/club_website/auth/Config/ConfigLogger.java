package club_website.auth.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ConfigLogger {

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @jakarta.annotation.PostConstruct
    public void logConfig() {
        System.out.println("Database URL: " + databaseUrl);
    }
}
