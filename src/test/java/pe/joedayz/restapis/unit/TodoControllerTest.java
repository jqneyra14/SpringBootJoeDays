package pe.joedayz.restapis.unit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pe.joedayz.restapis.controllers.TodoController;
import pe.joedayz.restapis.domains.Todo;
import pe.joedayz.restapis.services.TodoService;
import pe.joedayz.restapis.services.TodoTypeService;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TodoService todoService;

    @MockBean
    private TodoTypeService todoTypeService;

    @Test
    public void givenTodo_whenGetTodo_thenReturnJson() throws Exception {
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setTitle("Tender mi cama");
        todo.setDateCreated(new Date());
        todo.setDueDate(new Date());

        given(todoService.findById(1L)).willReturn(todo);

        //when-then
        mvc.perform(get("/api/todo/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(todo.getTitle())));

    }
}
