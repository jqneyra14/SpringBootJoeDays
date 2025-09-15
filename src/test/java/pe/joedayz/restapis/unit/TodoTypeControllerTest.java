package pe.joedayz.restapis.unit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pe.joedayz.restapis.controllers.TodoTypeController;
import pe.joedayz.restapis.domains.TodoType;
import pe.joedayz.restapis.services.TodoService;
import pe.joedayz.restapis.services.TodoTypeService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoTypeController.class)
public class TodoTypeControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private TodoService todoService;

    @MockBean
    private TodoTypeService todoTypeService;

    @Test
    public void  givenTodoType_whenPostTodoType_thenStatus200() throws Exception{

        TodoType personal = new TodoType();
        personal.setCode("PERSONAL");
        personal.setDescription("Personal tasks");

        given(todoTypeService.create(personal)).willReturn(personal);

        //when-then
        mvc.perform(post("/api/todoType")
                .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\": \"personal\", \"description\": \"Personal tasks\"}"))
                .andExpect(status().isOk());
    }

}
