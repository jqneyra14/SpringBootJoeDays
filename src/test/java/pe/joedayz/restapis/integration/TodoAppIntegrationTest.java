package pe.joedayz.restapis.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pe.joedayz.restapis.domains.TodoType;
import pe.joedayz.restapis.services.TodoTypeService;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoAppIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TodoTypeService todoTypeService;

    TodoType personal;

    @BeforeEach
    void createTodoType() throws Exception {
        personal = new TodoType();
        personal.setCode("PERSONAL");
        personal.setDescription("Personal tasks");

        mockMvc.perform(post("/api/todoType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personal)))
                .andExpect(status().isOk());
    }

    @Test
    void testTodoTypeCreateThroughAllLayers() throws Exception{

        TodoType todoType = todoTypeService.findByCode("PERSONAL");
        assertEquals(personal.getCode(), todoType.getCode());
    }

}
