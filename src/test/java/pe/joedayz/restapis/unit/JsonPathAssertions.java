package pe.joedayz.restapis.unit;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class JsonPathAssertions {


    @Test
    void todoAssertion(){
        //given
        String todoStr = "{\"id\":1," +
                "\"title\":\"Do Laundry\"," +
                "\"done\":true," +
                "\"dateCreated\":\"09/01/2022 03:20\"," +
                "\"dateDone\":\"07/04/2022 01:59\"," +
                "\"dueDate\":\"10/01/2022 04:00\"," +
                "\"lastUpdated\":\"07/04/2022 01:59\"," +
                "\"type\":null}";

        //When
        Map<String, String> todoMap = JsonPath.read(todoStr, "$");

        //Then
        assertNotNull(todoMap);
        assertInstanceOf(Map.class, todoMap);
        assertEquals("Do Laundry", todoMap.get("title"));
        assertEquals("09/01/2022 03:20", todoMap.get("dateCreated"));
        assertEquals("07/04/2022 01:59", todoMap.get("dateDone"));
        assertEquals("10/01/2022 04:00", todoMap.get("dueDate"));
        assertEquals("07/04/2022 01:59", todoMap.get("lastUpdated"));
        assertEquals(true, todoMap.get("done"));
        assertNull(todoMap.get("type"));
    }

    @Test
    void todoWithTodoTypeAssertion(){
        String todoStr = "{\"id\":134," +
                "\"title\":\"Do Laundry\"," +
                "\"done\":false," +
                "\"dateCreated\":\"09/01/2022 01:20\"," +
                "\"dateDone\":null," +
                "\"dueDate\":\"10/01/2022 02:00\"," +
                "\"lastUpdated\":null," +
                "\"type\":{\"description\":\"Todo for Personal Work\",\"code\":\"PERSONAL\"}}";

        Map<String, String> todoMap = JsonPath.read(todoStr, "$");
        Map<String, String> todoTypeMap = JsonPath.read(todoStr, "$.type");

        assertNotNull(todoMap);
        assertNotNull(todoTypeMap);
        assertEquals("PERSONAL", todoTypeMap.get("code"));
    }

}
