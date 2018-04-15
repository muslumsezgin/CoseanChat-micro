package me.cosean.chat;

import me.cosean.chat.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class ChatApplication implements CommandLineRunner {

	@Resource
	private
	StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}
}
