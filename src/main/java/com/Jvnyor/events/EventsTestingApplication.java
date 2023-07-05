package com.Jvnyor.events;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.nio.file.Files;
import java.nio.file.Paths;

@EnableScheduling
@SpringBootApplication
public class EventsTestingApplication {

	@Autowired
	private JsonProducer jsonProducer;

	public static void main(String[] args) {
		SpringApplication.run(EventsTestingApplication.class, args);
	}

	@SneakyThrows
	@Scheduled(fixedRate = 5000)
	public void kafkaSender() {
		String json = new String(Files.readAllBytes(Paths.get("src/main/resources/example.json")));
		jsonProducer.send(json);
	}
}
