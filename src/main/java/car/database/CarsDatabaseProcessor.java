package car.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CarsDatabaseProcessor {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(CarsDatabaseProcessor.class, args);
		applicationContext.getBean(EmailProcessor.class).processUnsentEmails();
	}

}
