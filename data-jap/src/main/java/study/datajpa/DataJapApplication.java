package study.datajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;


@EnableJpaAuditing
@SpringBootApplication
//@EnableJpaRepositories(basePackages = "study.datajpa.repository")
public class DataJapApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataJapApplication.class, args);
	}

	@Bean
	public AuditorAware<String> audtitorProvider(){
		return () -> Optional.of(UUID.randomUUID().toString());

	}
}
