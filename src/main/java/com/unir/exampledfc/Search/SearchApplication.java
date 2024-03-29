package com.unir.exampledfc.search;

import com.unir.exampledfc.search.service.DatabaseSeeder;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchApplication {
	private final DatabaseSeeder databaseSeeder;
	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class, args);
	}

	public SearchApplication(DatabaseSeeder databaseSeeder) {
		this.databaseSeeder = databaseSeeder;
	}

	@PostConstruct
	public void init() {
		databaseSeeder.seedData();
	}

}
