package pe.joedayz.restapis.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pe.joedayz.restapis.domains.Todo;
import pe.joedayz.restapis.domains.TodoType;



public interface TodoTypeRepository extends CrudRepository<TodoType, String>, PagingAndSortingRepository<TodoType, String> {

}
