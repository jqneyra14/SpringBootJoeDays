package pe.joedayz.restapis.repositories;

import org.springframework.data.repository.CrudRepository;
import pe.joedayz.restapis.domains.Todo;

public interface  TodoRepository extends CrudRepository<Todo, Long> {
}
