
package pe.joedayz.restapis.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pe.joedayz.restapis.domains.Todo;

@RepositoryRestResource
public interface TodoRestRepository extends CrudRepository<Todo, Long>, PagingAndSortingRepository<Todo, Long>{

}
