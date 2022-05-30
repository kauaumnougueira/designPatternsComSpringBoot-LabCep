package KauaNog.projetoLabCep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ProjetoLabCepApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoLabCepApplication.class, args);
	}

}
