package pe.joedayz.restapis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.web.client.RestTemplate;
import pe.joedayz.restapis.domains.Todo;

// ComponentScan: controllers, services, repositories para agregar al classpath
// EnableAutoConfiguration: Configura automaticamente la aplicacion
// SpringBootConfiguration: Clase de configuracion de Spring Boot
// SpringBootApplication: Anotacion que combina las tres anteriores
@SpringBootApplication
public class RestapisApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapisApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public  RepresentationModelProcessor<EntityModel<Todo>> todoProcessor(){

		return new RepresentationModelProcessor<EntityModel<Todo>>() {
			@Override
			public EntityModel<Todo> process(EntityModel<Todo> model) {
				model.add(Link.of("http://localhost:8080/todoTypes", "types"));
				return model;
			}
		};
	}

}
