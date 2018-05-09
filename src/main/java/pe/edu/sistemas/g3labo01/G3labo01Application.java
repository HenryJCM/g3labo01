package pe.edu.sistemas.g3labo01;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication; 
import org.springframework.web.bind.annotation. *;

@SpringBootApplication 
@RestController
public class G3labo01Application {

	public static void main(String[] args) {
		SpringApplication.run(G3labo01Application.class, args);
	}
	
	@GetMapping ("/") 
    public String hello() { 
            return "Hello Spring Boot!"; 
    } 
}
