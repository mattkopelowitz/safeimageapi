package com.mattkopelowitz.safeimageapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class SafeimageapiApplication {

	public static void main(String[] args) {
		String env = System.getenv("ENV");
		if (env == null || env.equalsIgnoreCase("dev")) {
			Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
			dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
			// Set AWS SDK expected system properties
			System.setProperty("aws.accessKeyId", System.getProperty("AWS_ACCESS_KEY_ID", ""));
			System.setProperty("aws.secretAccessKey", System.getProperty("AWS_SECRET_ACCESS_KEY", ""));
		}
		SpringApplication.run(SafeimageapiApplication.class, args);
	}
}