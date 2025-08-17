package pe.joedayz.restapis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ComponentScan: controllers, services, repositories para agregar al classpath
// EnableAutoConfiguration: Configura automaticamente la aplicacion
// SpringBootConfiguration: Clase de configuracion de Spring Boot
// SpringBootApplication: Anotacion que combina las tres anteriores
@SpringBootApplication
public class RestapisApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapisApplication.class, args);
	}

}
