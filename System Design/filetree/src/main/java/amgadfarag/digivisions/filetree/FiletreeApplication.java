package amgadfarag.digivisions.filetree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"amgadfarag.digivisions.filetree.controllers","amgadfarag.digivisions.filetree.*"})
@SpringBootApplication
public class FiletreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FiletreeApplication.class, args);
	}

}
