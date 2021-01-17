package br.com.nextmove.condonext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class CondonextApplication {

	public static void main(String[] args) {
		SpringApplication.run(CondonextApplication.class, args);
	}

}
