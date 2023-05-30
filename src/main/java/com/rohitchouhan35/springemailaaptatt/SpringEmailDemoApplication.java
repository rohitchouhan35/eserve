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

@SpringBootApplication
@EnableScheduling
public class SpringEmailDemoApplication {

	@Autowired
	private EmailSenderService senderService;

	private static int temp;

	public static void main(String[] args) throws IOException, InterruptedException {
		temp = CityTemperature.getTemperature("Seattle");
		SpringApplication.run(SpringEmailDemoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
		sendTemperatureEmail();
	}

	@Scheduled(fixedRate = 10000) // Execute every 10 seconds (in milliseconds)
	public void sendTemperatureEmail() throws MessagingException {
		String data = "Your city's temperature is " + String.valueOf(temp);
		senderService.sendSimpleEmail("rochauhan35@gmail.com", data, "Weather info");
	}
}
