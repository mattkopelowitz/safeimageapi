package com.mattkopelowitz.safeimageapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class SafeimageapiApplication {

	public static void main(String[] args) {
		// Only load .env file when running locally (i.e., in development)
		String env = System.getenv("ENV");
		if (env == null || env.equalsIgnoreCase("dev")) {
			Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
			dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		}

		SpringApplication.run(SafeimageapiApplication.class, args);
	}
}

