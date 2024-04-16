package mk.ukim.finki.vebaud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class VebAudApplication {

    public static void main(String[] args) {
        SpringApplication.run(VebAudApplication.class, args);
    }

}
