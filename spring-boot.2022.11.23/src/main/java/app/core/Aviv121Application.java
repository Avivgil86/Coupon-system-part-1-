package app.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Aviv121Application {

	public static void main(String[] args) {
		SpringApplication.run(Aviv121Application.class, args);
		System.out.println("Server is running");
		
	}

}
