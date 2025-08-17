package pe.joedayz.restapis.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.joedayz.restapis.domains.TodoType;

@Repository
public interface TodoTypeRepository extends CrudRepository<TodoType, String> {

}
