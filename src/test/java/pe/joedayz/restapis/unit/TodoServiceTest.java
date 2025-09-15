package pe.joedayz.restapis.unit;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pe.joedayz.restapis.domains.Todo;
import pe.joedayz.restapis.repositories.TodoRepository;
import pe.joedayz.restapis.services.TodoService;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TodoServiceTest {


    private TodoRepository todoRepository = Mockito.mock(TodoRepository.class);

    private TodoService todoService = new TodoService(todoRepository);

    @Test
    public void whenUpdate_thenReturnTodo(){

        Todo doLaundry = new Todo();
        doLaundry.setId(1L);
        doLaundry.setTitle("Do Laundry");
        doLaundry.setDone(true);
        doLaundry.setDateCreated(new Date());
        doLaundry.setDueDate(new Date());

        //given
        Mockito.when(todoRepository.save(doLaundry)).thenReturn(doLaundry);

        //when
        Todo result = todoService.update(doLaundry);

        //then
        assertNotNull(result.getDateDone());
    }
}
