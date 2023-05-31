package com.rohitchouhan35.springemailaaptatt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class SpringEmailDemoApplication {

	@Autowired
	private EmailSenderService senderService;

	public static List<String> emailList = new ArrayList<>();

	public static void main(String[] args) throws IOException, InterruptedException {
		String version = System.getProperty("java.version");
		System.out.println("Java version: " + version);
		SpringApplication.run(SpringEmailDemoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException, IOException, InterruptedException {
		emailList = new ArrayList<>();
		GetUsersEmailList.addUsersToList(emailList);
		sendTemperatureEmails();
	}

	@Scheduled(fixedRate = 10000) // executes every 10 seconds
	public void sendTemperatureEmails() throws MessagingException, IOException, InterruptedException {
		String data = CityTemperature.getData();

		for (String email : emailList) {
			senderService.sendSimpleEmail(email, data, "Weather info");
		}
	}
}
