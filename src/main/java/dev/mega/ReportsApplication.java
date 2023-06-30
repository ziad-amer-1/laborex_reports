package dev.mega;

import dev.mega.entity.Telesales;
import dev.mega.repository.TelesalesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ReportsApplication {

	private final TelesalesRepo telesalesRepo;

	public ReportsApplication(TelesalesRepo telesalesRepo) {
		this.telesalesRepo = telesalesRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(ReportsApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {

			Telesales telesales1 = new Telesales();
			telesales1.setName("ziad");
			telesales1.setCode("1801");
			telesales1.setWorkingPeriods(Set.of("AM"));
			telesales1.setWorkingRegions(Set.of("tanta"));

			Telesales telesales2 = new Telesales();
			telesales2.setName("ahmed");
			telesales2.setCode("1802");
			telesales2.setWorkingPeriods(Set.of("PM"));
			telesales2.setWorkingRegions(Set.of("mahalla"));

			Telesales telesales3 = new Telesales();
			telesales3.setName("aya");
			telesales3.setCode("1803");
			telesales3.setWorkingPeriods(Set.of("PM", "AM"));
			telesales3.setWorkingRegions(Set.of("tanta", "mahalla"));

			this.telesalesRepo.saveAll(List.of(telesales1, telesales2, telesales3));

		};
	}

}
