package pe.joedayz.restapis.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pe.joedayz.restapis.domains.Todo;
import pe.joedayz.restapis.domains.TodoType;


public interface TodoRepository extends CrudRepository<Todo, Long>, PagingAndSortingRepository<Todo, Long> {

  Todo findByTitle(String title);

  Todo findByDateCreatedGreaterThanEqual(Date dateCreated);

  Todo findByDoneAndDateDone(Boolean done, Date dateDone);

  // Spring Data está permitiendo en lugar de findBy: readBy y getBy

  long countByDueDateLessThan(Date dueDate);

  long countByDateCreatedGreaterThanAndDueDate(Date dateCreated, Date dueDate);


  void deleteById(long id);

  long deleteByTitleAndDone(String title, Boolean done);

  @Query("SELECT t FROM Todo t WHERE t.done = true")
  List<Todo> readAllDone();

  @Query("SELECT t FROM Todo t WHERE t.dateCreated >= ?1 AND t.dueDate = ?2")
  List<Todo> fetchTodos(Date dateCreated, Date dueDate);

  //Ahora ya puedo integrar mis Named Queries en Spring Data

  List<Todo> fetchAllDone();

  List<Todo> fetchAllByName(String title);

  List<Todo>  findAllByTodoType(TodoType personal);
}
