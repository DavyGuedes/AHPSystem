package br.uece.engenharia.software.AHPSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AhpSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AhpSystemApplication.class, args);
	}
}
