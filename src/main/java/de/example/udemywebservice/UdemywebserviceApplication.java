package de.example.udemywebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UdemywebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdemywebserviceApplication.class, args);
	}

}

/*<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-security</artifactId>
</dependency>*/

/* -> auth is needed!
- in application properties username and password can be set
- every request need Auth
 */
