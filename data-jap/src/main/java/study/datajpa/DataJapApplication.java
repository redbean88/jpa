package study.datajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "study.datajpa.repository")
public class DataJapApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataJapApplication.class, args);
	}

}
