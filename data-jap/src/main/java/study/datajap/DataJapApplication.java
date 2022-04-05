package study.datajap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "study.datajpa.repository")
public class DataJapApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataJapApplication.class, args);
	}

}
