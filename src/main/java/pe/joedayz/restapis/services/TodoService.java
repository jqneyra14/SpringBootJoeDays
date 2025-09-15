package pe.joedayz.restapis.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import pe.joedayz.restapis.domains.Todo;
import pe.joedayz.restapis.events.TodoCreationEvent;
import pe.joedayz.restapis.repositories.TodoRepository;

@Service  // proveernos una instancia SINGLETON de esta clase
public class TodoService {


  private TodoRepository todoRepository;


  @Autowired
  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }


  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleTodoCreationEvent(TodoCreationEvent todoCreationEvent) {
    System.out.println("Handled TodoCreationEvent....");
  }


  @Transactional
  public Todo create(Todo todo) {
    todo.afterSave(); // registrar el TodoCreationEvent
    return todoRepository.save(todo);
  }

  public Todo findById(Long id) {
    Optional<Todo> todoResult = todoRepository.findById(id);
    if(todoResult.isPresent()){
      return todoResult.get();
    }else{
      return null;
    }
  }

  public Todo update(Todo todo) {
    todo.setLastUpdated(new Date());
    if(todo.isDone()){
      todo.setDateDone(new Date());
    }
    todo = todoRepository.save(todo);
    return todo;
  }

  public void deleteById(Long id) throws Exception{
    if(!todoRepository.existsById(id)){
      throw new Exception("No existe ese Id");
    }
    todoRepository.deleteById(id);
  }

  public List<Todo> findAll(String sort, Sort.Direction order, int pageNumber, int numOfRecords){
    Sort idDesc = Sort.by(order, sort);
    Pageable pageable = PageRequest.of(pageNumber, numOfRecords, idDesc);
    Page<Todo> todoPages = todoRepository.findAll(pageable);
    return todoPages.getContent();
  }
}
