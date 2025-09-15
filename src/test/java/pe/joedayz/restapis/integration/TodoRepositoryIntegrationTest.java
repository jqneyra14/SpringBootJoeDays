package pe.joedayz.restapis.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pe.joedayz.restapis.domains.Todo;
import pe.joedayz.restapis.domains.TodoType;
import pe.joedayz.restapis.repositories.TodoRepository;
import pe.joedayz.restapis.repositories.TodoTypeRepository;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class TodoRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoTypeRepository todoTypeRepository;

    @Test
    public void whenFindAllByType_thenReturnAllTodos(){
        //given
        TodoType personal = new TodoType();
        personal.setCode("PERSONAL");
        personal.setDateCreated(new Date());

        entityManager.persist(personal);

        Todo todo = new Todo();
        todo.setTitle("Tender mi cama");
        todo.setDateCreated(new Date());
        todo.setDueDate(new Date());
        todo.setTodoType(personal);

        Todo[] todos = new Todo[1];
        todos[0] = todo;

        entityManager.persist(todo);
        entityManager.flush();


        //when
        List<Todo> found = todoRepository.findAllByTodoType(personal);

        //then
        assertArrayEquals(todos, found.toArray());
    }

    @Test
    public void givenTodoObjIsPersisted_whenFindByTitle_thenReturnTodoObj(){
        Todo todo = new Todo();
        todo.setTitle("Test Todo");
        todo.setDescription("Test Description");
        todo.setDateCreated(new Date());
        todo.setDueDate(new Date());

        entityManager.persist(todo);
        entityManager.flush(); // Fuerzas a que se ejecute la persistencia

        //when
        Todo found = todoRepository.findByTitle(todo.getTitle());

        assertEquals(todo.getTitle(), found.getTitle());
    }


    @Test
    public void whenTodoObjIsSaved_thenTodoObjIsPersisted(){
        Todo todo = new Todo();
        todo.setTitle("Test Todo");
        todo.setDescription("Test Description");
        todo.setDateCreated(new Date());
        todo.setDueDate(new Date());

        //when
        todoRepository.save(todo);

        //then
        assertEquals(todo, todoRepository.findById(todo.getId()).get());

    }
}
