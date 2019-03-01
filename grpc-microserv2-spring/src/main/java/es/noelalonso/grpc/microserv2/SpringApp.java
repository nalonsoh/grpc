package es.noelalonso.grpc.microserv2;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class SpringApp {
	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(SpringApp.class,args);
	}
}
