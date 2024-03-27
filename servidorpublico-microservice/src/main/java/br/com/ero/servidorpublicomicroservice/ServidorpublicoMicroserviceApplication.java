package br.com.ero.servidorpublicomicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServidorpublicoMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServidorpublicoMicroserviceApplication.class, args);
	}

}
