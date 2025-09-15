package pe.joedayz.restapis.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import pe.joedayz.restapis.domains.TodoType;

import java.util.List;

//@RepositoryRestResource
public interface TodoTypeRestRepository extends CrudRepository<TodoType, String>,
    PagingAndSortingRepository<TodoType, String> {

}
